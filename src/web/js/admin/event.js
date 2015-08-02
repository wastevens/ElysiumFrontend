angular.module('admin.event.services', []);
angular.module('admin.event.sources', ['admin.event.services']);

angular.module('admin.event.controllers', ['admin.event.services', 'admin.event.sources']).
controller('manageEvents', ['$scope', function($scope) {
	
	$scope.submit = function() {
	}
}]);

angular.module('admin.event.directives', ['admin.event.services']).
directive('manageEvents', [function() {
	return {
		restrict: 'E',
		templateUrl: '/js/admin/event/display.html'
	};
}]);

angular.module('user.event.filters', []);

angular.module('admin.event', ['admin.event.controllers', 'admin.event.directives', 'admin.event.services', 'admin.event.sources', 'user.event.filters']);