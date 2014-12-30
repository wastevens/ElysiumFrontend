angular.module('sources.status.player', []).
factory('playerStatusSource', [function() {
	var map = [];
	
	map[0] = "Primary";
	map[1] = "Secondary";
	map[2] = "Inactive";
	
	return {
		get: function() {
			return map;
		}
	}
}]);