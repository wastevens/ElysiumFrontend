angular.module('sources.techniques', []).
factory('techniqueSource', [function() {
	var map = [];
	var i = -1;
	map[++i] = {"name": "An Da Shealladh", "ordinal": i};
    map[++i] = {"name": "Animal Succulence", "ordinal": i};
    map[++i] = {"name": "Animal Swarm", "ordinal": i};
    map[++i] = {"name": "Armor of Darkness", "ordinal": i};
    map[++i] = {"name": "Banshee's Wail", "ordinal": i};
    map[++i] = {"name": "Blind the Sun", "ordinal": i};
    map[++i] = {"name": "Bull's Eye", "ordinal": i};
    map[++i] = {"name": "Control the Savage Beast", "ordinal": i};
    map[++i] = {"name": "Death's Grip", "ordinal": i};
    map[++i] = {"name": "Denial of Aphrodite's Favor", "ordinal": i};
    map[++i] = {"name": "Devious Feint", "ordinal": i};
    map[++i] = {"name": "Echo Psychosis", "ordinal": i};
    map[++i] = {"name": "Fearful Blow", "ordinal": i};
    map[++i] = {"name": "Feast of Shadows", "ordinal": i};
    map[++i] = {"name": "Fenrir's Boon", "ordinal": i};
    map[++i] = {"name": "Fist of Stone", "ordinal": i};
    map[++i] = {"name": "Flames Bane", "ordinal": i};
    map[++i] = {"name": "Guardian Lion", "ordinal": i};
    map[++i] = {"name": "Instinctive Command", "ordinal": i};
    map[++i] = {"name": "Ligeia's Lament", "ordinal": i};
    map[++i] = {"name": "Mercurial Vitality", "ordinal": i};
    map[++i] = {"name": "Monologue", "ordinal": i};
    map[++i] = {"name": "Misplaced Affection", "ordinal": i};
    map[++i] = {"name": "Misleading Wounds", "ordinal": i};
    map[++i] = {"name": "Nightingales Song", "ordinal": i};
    map[++i] = {"name": "Quickened Blood", "ordinal": i};
    map[++i] = {"name": "Radiant Gaze", "ordinal": i};
    map[++i] = {"name": "Reflection of Endurance", "ordinal": i};
    map[++i] = {"name": "Reflection of Mercy", "ordinal": i};
    map[++i] = {"name": "Relentless Pursuit", "ordinal": i};
    map[++i] = {"name": "Retain the Quick Blood", "ordinal": i};
    map[++i] = {"name": "Second Wind", "ordinal": i};
    map[++i] = {"name": "Sympathetic Agony", "ordinal": i};
    map[++i] = {"name": "Telepathic Directive", "ordinal": i};
    map[++i] = {"name": "Unnatural Grace", "ordinal": i};
    map[++i] = {"name": "Visions of the True Form", "ordinal": i};
    map[++i] = {"name": "Will to Survive", "ordinal": i};
    map[++i] = {"name": "Wolf's Blood", "ordinal": i};
			
	return {
		get: function() {
			return map;
		}
	}	
}]);