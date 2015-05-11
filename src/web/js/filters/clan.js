angular.module('filters.clan', []).
filter('clan', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Clan'][input];
	};
}]);