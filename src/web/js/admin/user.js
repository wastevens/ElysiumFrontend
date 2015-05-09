angular.module('admin.user.services', ['admin.services.users', 'admin.services.patronage']);
angular.module('admin.user.sources', ['admin.user.services', 'sources.paymenttypes']);

function _loadUsers(scope, userRepository) {
	userRepository.getUsers().then(function(users) {
		scope.users = users;
		scope.users.forEach(function(user, index, array) {
			user.type = 'Client';
			user.typeId = 3;
			if(user.membershipId) {
				if(user.activePatron) {
					user.typeId = 1;
					user.type = 'Active Patron';
				} else {
					user.typeId = 2;
					user.type = 'Inactive Patron';
				}
			}
			if(scope.selectedUser && scope.selectedUser.id == user.id) {
				scope.selectedUser = user;
				scope.changeUser();
			}
		});
		scope.users.sort(function(userA, userB) {
			var typeCodeComparison = userA.typeId - userB.typeId;
			if(typeCodeComparison == 0) {
				if(userA.email < userB.email) return -1;
				if(userA.email > userB.email) return 1;
				if(userA.id < userB.id) return -1;
				if(userA.id > userB.id) return 1;
				return 0;				
			}
			return typeCodeComparison;
		});
	});
}

angular.module('admin.user.controllers', ['admin.user.services', 'admin.user.sources']).
controller('manageUsers', ['$scope', 'userRepository', 'patronageRepository', 'paymentTypesSource', function($scope, userRepository, patronageRepository, paymentTypesSource) {
	$scope.paymentTypes = paymentTypesSource.get();
	_loadUsers($scope, userRepository);
	$scope.changeUser = function() {
		$scope.newPayment = {};
		$scope.selectedPatronage = null;
		$scope.patronages = null;
		if($scope.selectedUser.membershipId) {
			patronageRepository.getPatronage($scope.selectedUser.membershipId).then(function(patronage, status, headers, config) {
				$scope.selectedPatronage = patronage;
			});
		} else {
			patronageRepository.getUnassignedPatronages().then(function(patronages, status, headers, config) {
				$scope.patronages = patronages;
				var addNewPatronage = {
						membershipId: 'Create New Patronage',
						activePatron: false,
						expiration: '',
						originalUsername: '',
						payments: []
				}
				$scope.patronages.unshift(addNewPatronage)
			});
		}
	}
	
	$scope.changePatronage = function() {
	}
	
	$scope.addPayment = function() {
		var paymentToAdd = {
			"paymentDate": $scope.newPayment.paymentDate,
			"paymentAmount": $scope.newPayment.paymentAmount,
			"paymentType": $scope.newPayment.paymentType,
			"paymentReceiptIdentifier": $scope.newPayment.paymentReceiptIdentifier
		};
		$scope.selectedPatronage.payments.push(paymentToAdd);
		$scope.newPayment = {};
	}
	
	$scope.removePayment = function(index) {
		$scope.selectedPatronage.payments.splice(index, 1);
	}
	
	$scope.submit = function() {
		var promise = null;
		if($scope.selectedUser.membershipId) {
			promise = patronageRepository.updatePatronage($scope.selectedPatronage);
		} else if ($scope.selectedPatronage) {
			$scope.selectedPatronage.userId = $scope.selectedUser.id;
			if($scope.selectedPatronage.membershipId === 'Create New Patronage') {
				var patronageToCreate = $scope.selectedPatronage;
				patronageToCreate.membershipId = null;
				patronageToCreate.year = new Date().getFullYear();
				promise = patronageRepository.createPatronage(patronageToCreate);
			} else {
				promise = patronageRepository.updatePatronage($scope.selectedPatronage);
			}
		}
		
		if(promise) {
			promise.then(function(updatedPatronage, status, headers, config) {
				$scope.selectedUser.membershipId = updatedPatronage.membershipId;
				promise = userRepository.updateUser($scope.selectedUser);
			});
		} else {
			promise = userRepository.updateUser($scope.selectedUser);
		}
		
		promise.then(function(updatedUser, status, headers, config) {
			_loadUsers($scope, userRepository);
		});
	}
}]);

angular.module('admin.user.directives', ['admin.user.services']).
directive('manageUsers', [function() {
	return {
		restrict: 'E',
		templateUrl: '/js/admin/user/display.html'
	};
}]);

angular.module('user.user.filters', ['filters.payment_type']);

angular.module('admin.user', ['admin.user.controllers', 'admin.user.directives', 'admin.user.services', 'admin.user.sources', 'user.user.filters']);