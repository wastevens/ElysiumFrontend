angular.module('admin.user.services', ['ngResource', 'services.csrfResource']).
factory('userRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/admin/users',
		getUsers: function() {
			return $resource(this.url, {}).query();
		}
	};
}]);

angular.module('admin.user.controllers', []);

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
}]);

angular.module('admin.user.filters', ['filters.role', 'filters.joinBy']);

angular.module('admin.user', ['admin.user.filters', 'admin.user.controllers', 'admin.user.directives', 'admin.user.services']);