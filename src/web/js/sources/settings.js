angular.module('sources.settings', []).
factory('settingsSource', [function() {
	var map = [];
	map[0] = "Camarilla";
	map[1] = "Anarch";
	map[2] = "Sabbat";
	return {
		get: function() {
			return map;
		}
	}
}]);