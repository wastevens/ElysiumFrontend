angular.module('sources.backgrounds', []).
factory('backgroundSource', [function() {
	var map = [];
	var i = -1;
	map[++i] = {"name": "Allies", "ordinal": i, "requiresSpecialization": false};
	map[++i] = {"name": "Alternate Identity", "ordinal": i, "requiresSpecialization": true};
    map[++i] = {"name": "Contacts", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Fame", "ordinal": i, "requiresSpecialization": true};
    map[++i] = {"name": "Generation", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Haven", "ordinal": i, "requiresSpecialization": true};
    map[++i] = {"name": "Herd", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Elite Influence", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Underworld Influence", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Resources", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Retainer", "ordinal": i, "requiresSpecialization": true};
    map[++i] = {"name": "Rituals", "ordinal": i, "requiresSpecialization": false};
			
	return {
		get: function() {
			return map;
		}
	}	
}]);