angular.module('sources.clans', []).
factory('clanSource', [function() {
	var map = [];
	
    map[0]  = {"name": "Assamite"};
    map[1]  = {"name": "Brujah"};
    map[2]  = {"name": "Follower of Set"};
    map[3]  = {"name": "Gangrel"};
    map[4]  = {"name": "Giovanni"};
    map[5]  = {"name": "Lasombra"};
    map[6]  = {"name": "Malkavian"};
    map[7]  = {"name": "Nosferatu"};
    map[8]  = {"name": "Toreador"};
    map[9]  = {"name": "Tremere"};
    map[10] = {"name":  "Tzimisce"};
    map[11] = {"name":  "Ventrue"};
    map[12] = {"name":  "Catiff"};
    map[13] = {"name":  "Baali"};
    map[14] = {"name":  "Cappadocian"};
    map[15] = {"name":  "Ravnos"};
    map[16] = {"name":  "Salubri"};
    map[17] = {"name":  "Daughter of Cacophony"};
    map[18] = {"name":  "Gargoyle"};
		
	return {
		get: function() {
			return map;
		}
	}
}]);