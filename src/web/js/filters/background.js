angular.module('filters.background', []).
filter('background', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Background'][input.id];
	};
}]);