angular.module('filters.setting', ['sources.settings']).
filter('setting', ['settingsSource', function(settingsSource) {
	return function(input) {
		return settingsSource.get().valueFor(input);
	};
}]);