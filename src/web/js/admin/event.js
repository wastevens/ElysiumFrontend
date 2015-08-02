angular.module('admin.event.services', ['admin.services.events']);
angular.module('admin.event.sources', ['admin.event.services']);

angular.module('admin.event.controllers', ['admin.event.services', 'admin.event.sources']).
controller('eventsController', ['$scope', 'eventRepository', function($scope, eventRepository) {
	$scope.showEvent = function(id) {
		eventRepository.getEvent(id).then(function(event) {
			$scope.event = event;
		});
	};
	
	$scope.submit = function() {
		console.log("events controller submit")
	}
}]);

angular.module('admin.event.directives', ['admin.event.services']).
directive('listEvents', ['eventRepository', function(eventRepository) {
	return {
		restrict: 'E',
		link: function(scope, iElement, iAttrs) {
			eventRepository.getEvents().then(function(events) {
				scope.events = events;
				scope.event = events[0];
			});
		},
		templateUrl: '/js/admin/event/display.html'
	};
}]).
directive('manageEvent', [function() {
	return {
		restrict: 'E',
		scope: {
			event: '='
		},
		templateUrl: '/js/admin/event/manage.html'
	};
}]);

angular.module('user.event.filters', ['filters.vampire', 'filters.event']);

angular.module('admin.event', ['admin.event.controllers', 'admin.event.directives', 'admin.event.services', 'admin.event.sources', 'user.event.filters']);