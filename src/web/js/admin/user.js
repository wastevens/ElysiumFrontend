angular.module('admin.user.services', ['ngResource', 'services.csrfResource']).
factory('userRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/admin/users',
		getUsers: function() {
			return $resource(this.url, {}).query();
		},
		getUserWithId: function(id) {
			return csrfResource.get(this.url + '/' + id);
		},
		updateUser: function(userToUpdate, csrfHeader, csrfToken) {
			return csrfResource.put(this.url + '/' + userToUpdate.id, userToUpdate, csrfHeader, csrfToken);
		}
	};
}]);

angular.module('admin.user.controllers', ['admin.user.services']).
controller('manageUser', ['$scope', '$rootScope', function($scope, $rootScope) {
	$scope.manageUser = function(id, csrfHeader, csrfToken) {
		console.log("manage user with id " + id);
		$rootScope.$broadcast('userSelected', id);
	};
}]).
controller('updateUser', ['$scope', '$rootScope', 'userRepository', function($scope, $rootScope, userRepository) {
	$scope.submit = function(csrfHeader, csrfToken) {
		$scope.user.roles = [];
		for(var i=0;i<$scope.roles.length;i++) {
			if($scope.roles[i]) { $scope.user.roles.push(i) }
		}
		
		userRepository.updateUser($scope.user, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {$rootScope.$broadcast('userUpdated')}).
			error(function(data, status, headers, config) {console.log("user update failed")});
	};
}]);

angular.module('admin.user.directives', ['admin.user.services']).
directive('listUsers', ['userRepository', function(userRepository) {
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		link: function(scope, iElement, iAttrs) {
			scope.users = userRepository.getUsers();
			scope.$on('userUpdated', function(event) {
				scope.users = userRepository.getUsers();  
			});
		},
		templateUrl: '/js/admin/user/display.html'
	};
}]).
directive('modifyUser', ['userRepository', function(userRepository) {
	console.log("modify user directive");
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		link: function(scope, iElement, iAttrs) {
			console.log('modify user directive link');
			scope.$on('userSelected', function(event, id) {
				console.log('modify user action heard! ' + id);
				userRepository.getUserWithId(id).
					success(function(data, status, headers, config) {
						scope.user = data;
						scope.roles = [];
						for(var i=0;i<scope.user.roles.length;i++) {scope.roles[scope.user.roles[i]] = true}
					}).
					error(function(data, status, headers, config) {console.log("fetch user failed")});
			});
		},
		templateUrl: '/js/admin/user/manage.html'
	};
}]);

angular.module('admin.user.filters', ['filters.role', 'filters.joinBy']);

angular.module('admin.user', ['admin.user.filters', 'admin.user.controllers', 'admin.user.directives', 'admin.user.services']);