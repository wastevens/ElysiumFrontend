angular.module('sources.settings', []).
factory('settingsSource', [function() {
	var map = new BiDirectionalMap();
	map.
		with(0, "Camarilla").
		with(1, "Anarch").
		with(2, "Sabbat");
	return {
		get: function() {
			return map;
		}
	}
}]);