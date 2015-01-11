angular.module('sources.disciplines', []).
factory('disciplineSource', [function() {
	var map = [];
	var i = -1;
	map[++i] = {"name": "Animalism", "id": i};
	map[++i] = {"name": "Auspex", "id": i};
    map[++i] = {"name": "Celerity", "id": i};
    map[++i] = {"name": "Chimerstry", "id": i};
    map[++i] = {"name": "Daimoinon", "id": i};
    map[++i] = {"name": "Dementation", "id": i};
    map[++i] = {"name": "Dominate", "id": i};
    map[++i] = {"name": "Fortitude", "id": i};
    map[++i] = {"name": "Melpominee", "id": i};
    map[++i] = {"name": "Mytherceria", "id": i};
    map[++i] = {"name": "Obeah", "id": i};
    map[++i] = {"name": "Obfuscate", "id": i};
    map[++i] = {"name": "Obtenebration", "id": i};
    map[++i] = {"name": "Potence", "id": i};
    map[++i] = {"name": "Presence", "id": i};
    map[++i] = {"name": "Protean", "id": i};
    map[++i] = {"name": "Quietus", "id": i};
    map[++i] = {"name": "Serpentis", "id": i};
    map[++i] = {"name": "Temporsis", "id": i};
    map[++i] = {"name": "Thanatosis", "id": i};
    map[++i] = {"name": "Valeren", "id": i};
    map[++i] = {"name": "Vicissitude", "id": i};
    map[++i] = {"name": "Visceratika", "id": i};
    
    map[++i] = {"name": "Sepulchre Path", "id": i};
    map[++i] = {"name": "Bone Path", "id": i};
    map[++i] = {"name": "Ash Path", "id": i};
    map[++i] = {"name": "Mortis Path", "id": i};
    
    map[++i] = {"name": "Path of Blood", "id": i};
    map[++i] = {"name": "Path of Conjuring", "id": i};
    map[++i] = {"name": "Path of Corruption", "id": i};
    map[++i] = {"name": "Path of Elemental Mastery", "id": i};
    map[++i] = {"name": "Lure of Flames", "id": i};
    map[++i] = {"name": "Movement of the Mind", "id": i};
    map[++i] = {"name": "Path of Technomancy", "id": i};
    map[++i] = {"name": "Path of Weather Mastery", "id": i};
			
	return {
		get: function() {
			return map;
		}
	}	
}]);