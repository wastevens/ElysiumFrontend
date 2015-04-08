angular.module('admin.user.services', ['admin.services.users', 'admin.services.patronage']);

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
		});
		scope.users.sort(function(userA, userB) {
			var typeCodeComparison = userA.typeId - userB.typeId;
			if(typeCodeComparison == 0) {
				if(userA.email < userB.email) return -1;
				if(userA.email > userB.email) return 1;
				return 0;				
			}
			return typeCodeComparison;
		});
	});
}

angular.module('admin.user.controllers', ['admin.user.services']).
controller('manageUsers', ['$scope', 'userRepository', 'patronageRepository', function($scope, userRepository, patronageRepository) {
	_loadUsers($scope, userRepository);
	$scope.changeUser = function() {
		$scope.selectedUserPatronage = null;
		$scope.selectedPatronage = null;
		if($scope.selectedUser.membershipId) {
			patronageRepository.getPatronage($scope.selectedUser.membershipId).then(function(patronage, status, headers, config) {
				$scope.selectedUserPatronage = patronage;
			});
		} else {
			patronageRepository.getUnassignedPatronages().then(function(patronages, status, headers, config) {
				$scope.patronages = patronages;
			});
		}
	}
	
	$scope.changePatronage = function() {
	}
	
	$scope.submit = function() {
		if($scope.selectedUserPatronage) {
			patronageRepository.updatePatronage($scope.selectedUserPatronage);
		} else if ($scope.selectedPatronage) {
			$scope.selectedPatronage.userId = $scope.selectedUser.id;
			$scope.selectedUser.membershipId = $scope.selectedPatronage.membershipId;
			patronageRepository.updatePatronage($scope.selectedPatronage);
		}
		userRepository.updateUser($scope.selectedUser);
	}
	
}]);

angular.module('admin.user.directives', ['admin.user.services']).
directive('manageUsers', [function() {
	return {
		restrict: 'E',
		templateUrl: '/js/admin/user/display.html'
	};
}]);

angular.module('admin.user.filters', ['filters.role', 'filters.joinBy']);

angular.module('admin.user', ['admin.user.filters', 'admin.user.controllers', 'admin.user.directives', 'admin.user.services']);