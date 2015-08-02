angular.module('filters.event', []).
filter('eventStatus', [function() {
	return function(input) {
		return eventStatus[input.id];
	};
}]);