angular.module('sources.skills', []).
factory('skillSource', [function() {
	var map = [];
	var i = -1;
	map[++i] = {"name": "Academics", "ordinal": i, "requiresSpecialization": false};
	map[++i] = {"name": "Animal Ken", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Athletics", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Awareness", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Brawl", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Computer", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Crafts", "ordinal": i, "requiresSpecialization": true};
    map[++i] = {"name": "Dodge", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Drive", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Empathy", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Firearms", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Intimidation", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Investigation", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Leadership", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Linguistics", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Lore", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Medicine", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Melee", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Occult", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Performance", "ordinal": i, "requiresSpecialization": true};
    map[++i] = {"name": "Science", "ordinal": i, "requiresSpecialization": true};
    map[++i] = {"name": "Security", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Stealth", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Streetwise", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Subterfuge", "ordinal": i, "requiresSpecialization": false};
    map[++i] = {"name": "Survival", "ordinal": i, "requiresSpecialization": false};
			
	return {
		get: function() {
			return map;
		}
	}	
}]);