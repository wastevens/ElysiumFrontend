angular.module('admin.event.services', ['admin.services.events']);
angular.module('admin.event.sources', ['admin.event.services']);

angular.module('admin.event.controllers', ['admin.event.services', 'admin.event.sources']).
controller('manageEvents', ['$scope', 'eventRepository', function($scope, eventRepository) {
	$scope.submit = function() {
		console.log("manage events submit")
	}
}]).
controller('updateEvent', ['$scope', 'eventRepository', function($scope, eventRepository) {
	$scope.submit = function() {
		console.log("update event submit")
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
directive('listActiveEvents', ['eventRepository', function(eventRepository) {
	return {
		restrict: 'E',
		link: function(scope, iElement, iAttrs) {
			eventRepository.getActiveEvents().then(function(events) {
				scope.events = events;
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