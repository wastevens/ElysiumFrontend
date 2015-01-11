angular.module('sources.attributes.focuses', []).
factory('physicalFocusSource', [function() {
	var map = [];
	var i = -1;
    map[++i] = {"name": "Strength", "id": i};
    map[++i] = {"name": "Dexterity", "id": i};
    map[++i] = {"name": "Stamina", "id": i};
		
	return {
		get: function() {
			return map;
		}
	}
}]).
factory('socialFocusSource', [function() {
	var map = [];
	var i = -1;
    map[++i] = {"name": "Charisma", "id": i};
    map[++i] = {"name": "Manipulation", "id": i};
    map[++i] = {"name": "Appearance", "id": i};
		
	return {
		get: function() {
			return map;
		}
	}
}]).
factory('mentalFocusSource', [function() {
	var map = [];
	var i = -1;
    map[++i] = {"name": "Intelligence", "id": i};
    map[++i] = {"name": "Wits", "id": i};
    map[++i] = {"name": "Perception", "id": i};
		
	return {
		get: function() {
			return map;
		}
	}
}]);