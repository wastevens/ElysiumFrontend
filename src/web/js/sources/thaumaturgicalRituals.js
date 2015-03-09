angular.module('sources.rituals.thaumaturgical', []).
factory('thaumaturgicalRitualSource', [function() {
	var map = [];
	var i = -1;
    map[++i] = {"name": "Blood Mastery", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Bind the Accusing Tongue", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Communicate with Kindred Sire", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Defense of the Sacred Haven", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Engaging the Vessel of Transference", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Illuminate the Trail of Prey", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Principal Focus of Vitae Infusion", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Warding Circle", "rating": 1, "ordinal": i};
    map[++i] = {"name": "Banish Big Brother", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Burning Blade", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Craft Bloodstone", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Eyes of the Nighthawk", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Illusion of Peaceful Death", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Machine Blitz", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Recure of the Homeland", "rating": 2, "ordinal": i};
    map[++i] = {"name": "Detect the Hidden Observer", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Flesh of Fiery Touch", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Incorporeal Passage", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Mirror of Second Sight", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Pavis of Foul Presence", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Soul of the Homunculi", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Shaft of Belated Quiescence", "rating": 3, "ordinal": i};
    map[++i] = {"name": "Innocence of the Child's Heart", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Mirror Walk", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Severed Hand", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Scry", "rating": 4, "ordinal": i};
    map[++i] = {"name": "Blood Contract", "rating": 5, "ordinal": i};
    map[++i] = {"name": "Cobra's Favor", "rating": 5, "ordinal": i};
    map[++i] = {"name": "Paper Flesh", "rating": 5, "ordinal": i};
    map[++i] = {"name": "Stone of the True Form", "rating": 5, "ordinal": i};
    
	return {
		get: function() {
			return map;
		}
	}	
}]);