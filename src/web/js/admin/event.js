angular.module('admin.event.services', ['admin.services.events', 'services.traits', 'services.troupes']);
angular.module('admin.event.sources', ['admin.event.services', 'sources.vampire']);

angular.module('admin.event.controllers', ['admin.event.services', 'admin.event.sources']).
controller('eventsController', ['$scope', 'eventRepository', 'eventStatusSource', 'venueSource', 'troupeRepository', function($scope, eventRepository, eventStatusSource, venueSource, troupeRepository) {
	$scope.displayEvents = [];
	$scope.newEvent = {};
	
	$scope.events = [];
	eventRepository.getEvents().then(function(events) {
		$scope.events = events;
		$scope.events.forEach(function(event) {
			$scope.displayEvents[event.id] = true;
		});
	});
	
	$scope.eventStatuses = [];
	eventStatusSource.get().then(function(eventStatuses) {
		$scope.eventStatuses = eventStatuses;
	});
	
	$scope.venues = [];
	venueSource.get().then(function(venues) {
		$scope.venues = venues;
		if($scope.troupe && $scope.troupe.venue) {
			$scope.venue = $scope.venues[$scope.troupe.venue.id];
		}
	});
	
	$scope.troupes = troupeRepository.getTroupes();  
	
	$scope.showEvent = function(event) {
		$scope.event = event;
		if($scope.event.eventStatus) {
			$scope.eventStatuses.forEach(function(eventStatus) {
				if(eventStatus.id == $scope.event.eventStatus.id) {
					$scope.event.eventStatus = eventStatus;
				}
			});
		}
		if($scope.event.venue) {
			$scope.venues.forEach(function(venue) {
				if(venue.id == $scope.event.venue.id) {
					$scope.event.venue = venue;
				}
			});
		}
		if($scope.event.troupe) {
			$scope.troupes.forEach(function(troupe) {
				if(troupe.id == $scope.event.troupe.id) {
					$scope.event.troupe = troupe;
				}
			});
		}
		$scope.displayEvents[event.id] = false;
	}
	
	$scope.submit = function(event) {
		eventRepository.updateEvent(event).then(function(response) {
			eventRepository.getEvents().then(function(events) {
				$scope.events = events;
				$scope.events.forEach(function(event) {
					$scope.displayEvents[event.id] = true;
				});
			});
		});
	}
	$scope.create = function(event) {
		eventRepository.createEvent(event).then(function(response) {
			eventRepository.getEvents().then(function(events) {
				$scope.events = events;
				$scope.events.forEach(function(event) {
					$scope.displayEvents[event.id] = true;
				});
				$scope.newEvent = {};
			});
		});
	}
}]);

angular.module('admin.event.directives', ['admin.event.sources', 'admin.event.services']).
directive('listEvents', ['eventRepository', 'eventStatusSource', function(eventRepository, eventStatusSource) {
	return {
		restrict: 'E',
		templateUrl: '/js/admin/event/display.html'
	};
}]).
directive('manageEvent', [function() {
	return {
		restrict: 'E',
		templateUrl: '/js/admin/event/manage.html'
	};
}]);

angular.module('user.event.filters', ['filters.vampire']);

angular.module('admin.event', ['admin.event.controllers', 'admin.event.directives', 'admin.event.services', 'admin.event.sources', 'user.event.filters']);