angular.module('sources.roles', []).
factory('rolesSource', [function() {
	var map = [];
	
	map[0] = "Admin";
	map[1] = "Lead Storyteller";
	map[2] = "Assistant Storyteller";
	map[3] = "Narrator";
	map[4] = "Patron";
	map[5] = "User";
	
	return {
		get: function() {
			return map;
		}
	}
}]);