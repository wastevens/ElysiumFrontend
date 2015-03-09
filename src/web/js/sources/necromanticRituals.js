angular.module('sources.rituals.necromantic', []).
factory('necromanticRitualSource', [function() {
	var map = [];
	var i = -1;
    map[++i] = {"name": "Call of the Hungry Dead", "rating": 1, "ordinal": i};
    map[++i] = {"name": "circle of cerberus", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Dark Assistant", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Eyes of the Grave", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Smoking Mirror", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Warping the Morbid Visage", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Black Blood", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Din of the Damned", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Sepulchral Beacon", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Stained Sight", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Scales of Maat", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Moldering Presence", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Rise, Cerberus", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Spirit Beacon", "rating": 3, "ordinal": i};
    map[++i] = {"name": "The Servant Undying", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Bastone Diabolico", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Lethe's Water", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Ritual of Xipe Totec", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Strength of Rotten Flesh", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Chill of Oblivion", "rating": 5, "ordinal": i};
    map[++i] = {"name": "Weight of the Tomb", "rating": 5, "ordinal": i};
    
	return {
		get: function() {
			return map;
		}
	}	
}]);