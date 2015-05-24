angular.module('filters.discipline', []).
filter('discipline', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Discipline'][input.id];
	};
}]);