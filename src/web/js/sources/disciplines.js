angular.module('sources.disciplines', []).
factory('disciplineSource', [function() {
	var map = [];
	var i = -1;
	map[++i] = {"name": "Animalism", "ordinal": i};
	map[++i] = {"name": "Auspex", "ordinal": i};
    map[++i] = {"name": "Celerity", "ordinal": i};
    map[++i] = {"name": "Chimerstry", "ordinal": i};
    map[++i] = {"name": "Daimoinon", "ordinal": i};
    map[++i] = {"name": "Dementation", "ordinal": i};
    map[++i] = {"name": "Dominate", "ordinal": i};
    map[++i] = {"name": "Fortitude", "ordinal": i};
    map[++i] = {"name": "Melpominee", "ordinal": i};
    map[++i] = {"name": "Mytherceria", "ordinal": i};
    map[++i] = {"name": "Obeah", "ordinal": i};
    map[++i] = {"name": "Obfuscate", "ordinal": i};
    map[++i] = {"name": "Obtenebration", "ordinal": i};
    map[++i] = {"name": "Potence", "ordinal": i};
    map[++i] = {"name": "Presence", "ordinal": i};
    map[++i] = {"name": "Protean", "ordinal": i};
    map[++i] = {"name": "Quietus", "ordinal": i};
    map[++i] = {"name": "Serpentis", "ordinal": i};
    map[++i] = {"name": "Temporsis", "ordinal": i};
    map[++i] = {"name": "Thanatosis", "ordinal": i};
    map[++i] = {"name": "Valeren", "ordinal": i};
    map[++i] = {"name": "Vicissitude", "ordinal": i};
    map[++i] = {"name": "Visceratika", "ordinal": i};
    
    map[++i] = {"name": "Sepulchre Path", "ordinal": i};
    map[++i] = {"name": "Bone Path", "ordinal": i};
    map[++i] = {"name": "Ash Path", "ordinal": i};
    map[++i] = {"name": "Mortis Path", "ordinal": i};
    
    map[++i] = {"name": "Path of Blood", "ordinal": i};
    map[++i] = {"name": "Path of Conjuring", "ordinal": i};
    map[++i] = {"name": "Path of Corruption", "ordinal": i};
    map[++i] = {"name": "Path of Elemental Mastery", "ordinal": i};
    map[++i] = {"name": "Lure of Flames", "ordinal": i};
    map[++i] = {"name": "Movement of the Mind", "ordinal": i};
    map[++i] = {"name": "Path of Technomancy", "ordinal": i};
    map[++i] = {"name": "Path of Weather Mastery", "ordinal": i};
			
	return {
		get: function() {
			return map;
		}
	}	
}]);