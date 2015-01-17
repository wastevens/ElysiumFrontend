angular.module('sources.skills', []).
factory('skillSource', [function() {
	var map = [];
	var i = -1;
	map[++i] = {"name": "Academics", "id": i, "specialization": false};
	map[++i] = {"name": "Animal Ken", "id": i, "specialization": false};
    map[++i] = {"name": "Athletics", "id": i, "specialization": false};
    map[++i] = {"name": "Awareness", "id": i, "specialization": false};
    map[++i] = {"name": "Brawl", "id": i, "specialization": false};
    map[++i] = {"name": "Computer", "id": i, "specialization": false};
    map[++i] = {"name": "Crafts", "id": i, "specialization": true};
    map[++i] = {"name": "Dodge", "id": i, "specialization": false};
    map[++i] = {"name": "Drive", "id": i, "specialization": false};
    map[++i] = {"name": "Empathy", "id": i, "specialization": false};
    map[++i] = {"name": "Firearms", "id": i, "specialization": false};
    map[++i] = {"name": "Intimidation", "id": i, "specialization": false};
    map[++i] = {"name": "Investigation", "id": i, "specialization": false};
    map[++i] = {"name": "Leadership", "id": i, "specialization": false};
    map[++i] = {"name": "Linguistics", "id": i, "specialization": false};
    map[++i] = {"name": "Lore", "id": i, "specialization": false};
    map[++i] = {"name": "Medicine", "id": i, "specialization": false};
    map[++i] = {"name": "Melee", "id": i, "specialization": false};
    map[++i] = {"name": "Occult", "id": i, "specialization": false};
    map[++i] = {"name": "Performance", "id": i, "specialization": true};
    map[++i] = {"name": "Science", "id": i, "specialization": true};
    map[++i] = {"name": "Security", "id": i, "specialization": false};
    map[++i] = {"name": "Stealth", "id": i, "specialization": false};
    map[++i] = {"name": "Streetwise", "id": i, "specialization": false};
    map[++i] = {"name": "Subterfuge", "id": i, "specialization": false};
    map[++i] = {"name": "Survival", "id": i, "specialization": false};
			
	return {
		get: function() {
			return map;
		}
	}	
}]);