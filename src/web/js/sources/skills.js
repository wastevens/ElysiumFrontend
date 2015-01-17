angular.module('sources.skills', []).
factory('skillSource', [function() {
	var map = [];
	var i = -1;
	map[++i] = {"name": "Academics", "id": i, "requiresSpecialization": false};
	map[++i] = {"name": "Animal Ken", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Athletics", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Awareness", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Brawl", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Computer", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Crafts", "id": i, "requiresSpecialization": true};
    map[++i] = {"name": "Dodge", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Drive", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Empathy", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Firearms", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Intimidation", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Investigation", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Leadership", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Linguistics", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Lore", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Medicine", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Melee", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Occult", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Performance", "id": i, "requiresSpecialization": true};
    map[++i] = {"name": "Science", "id": i, "requiresSpecialization": true};
    map[++i] = {"name": "Security", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Stealth", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Streetwise", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Subterfuge", "id": i, "requiresSpecialization": false};
    map[++i] = {"name": "Survival", "id": i, "requiresSpecialization": false};
			
	return {
		get: function() {
			return map;
		}
	}	
}]);