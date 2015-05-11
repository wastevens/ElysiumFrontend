angular.module('filters.bloodline', []).
filter('bloodline', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Bloodline'][input];
	};
}]);