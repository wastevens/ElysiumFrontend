angular.module('filters.thaumaturgicalritual', []).
filter('thaumaturgical_ritual', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['ThaumaturgicalRitual'][input];
	};
}]);