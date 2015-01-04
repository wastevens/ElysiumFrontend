angular.module('sources.clans', []).
factory('clanSource', [function() {
	var map = [];
	var i = 0;
    map[i]  = {"name": "Assamite", "id": i++};
    map[i]  = {"name": "Brujah", "id": i++};
    map[i]  = {"name": "Follower of Set", "id": i++};
    map[i]  = {"name": "Gangrel", "id": i++};
    map[i]  = {"name": "Giovanni", "id": i++};
    map[i]  = {"name": "Lasombra", "id": i++};
    map[i]  = {"name": "Malkavian", "id": i++};
    map[i]  = {"name": "Nosferatu", "id": i++};
    map[i]  = {"name": "Toreador", "id": i++};
    map[i]  = {"name": "Tremere", "id": i++};
    map[i] = {"name":  "Tzimisce", "id": i++};
    map[i] = {"name":  "Ventrue", "id": i++};
    map[i] = {"name":  "Catiff", "id": i++};
    map[i] = {"name":  "Baali", "id": i++};
    map[i] = {"name":  "Cappadocian", "id": i++};
    map[i] = {"name":  "Ravnos", "id": i++};
    map[i] = {"name":  "Salubri", "id": i++};
    map[i] = {"name":  "Daughter of Cacophony", "id": i++};
    map[i] = {"name":  "Gargoyle", "id": i++};
		
	return {
		get: function() {
			return map;
		}
	}
}]);