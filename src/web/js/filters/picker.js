angular.module('filters.picker', []).
filter('picker', ['$filter', function($filter) {
	return function(input, filtername) {
		return $filter(filtername)(input);
	};
}]);