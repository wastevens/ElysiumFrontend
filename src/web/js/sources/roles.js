angular.module('sources.roles', []).
factory('rolesSource', [function() {
	var map = [];
	
	map[0] = "Admin";
	map[1] = "User";
	
	return {
		get: function() {
			return map;
		}
	}
}]);