angular.module('filters.necromanticritual', []).
filter('necromantic_ritual', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['NecromanticRitual'][input];
	};
}]);