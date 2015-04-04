angular.module('admin.user.services', ['admin.services.users']);

angular.module('admin.user.controllers', ['admin.user.services']).
controller('viewUsers', ['$scope', 'userRepository', function($scope, userRepository) {
	var foo = userRepository.getUsers();
	foo.$promise.then(function(users) {
		$scope.users = users;
		$scope.users.forEach(function(user, index, array) {
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
		$scope.users.sort(function(userA, userB) {
			var typeCodeComparison = userA.typeId - userB.typeId;
			if(typeCodeComparison == 0) {
				if(userA.email < userB.email) return -1;
				if(userA.email > userB.email) return 1;
				return 0;				
			}
			return typeCodeComparison;
		});
	});
}]).
controller('manageUser', ['$scope', '$rootScope', function($scope, $rootScope) {
}]).
controller('updateUser', ['$scope', '$rootScope', 'userRepository', function($scope, $rootScope, userRepository) {
}]);

angular.module('admin.user.directives', ['admin.user.services']).
directive('listUsers', ['userRepository', function(userRepository) {
	return {
		restrict: 'E',
		templateUrl: '/js/admin/user/display.html'
	};
}]).
directive('modifyUser', ['userRepository', function(userRepository) {
	return {
		restrict: 'E',
		templateUrl: '/js/admin/user/manage.html'
	};
}]);

angular.module('admin.user.filters', ['filters.role', 'filters.joinBy']);

angular.module('admin.user', ['admin.user.filters', 'admin.user.controllers', 'admin.user.directives', 'admin.user.services']);