angular.module('filters.joinBy', []).
filter('joinBy', [function() {
	return function(input, delimiter) {
		return (input || []).join(delimiter || ',');
	};
}]);