angular.module('filters.event', []).
filter('eventStatus', [function() {
	return function(input) {
		if(input) {
			return eventStatus[input.id];
		}
		return "";
	};
}]);