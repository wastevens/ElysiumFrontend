angular.module('sources.clans', []).
factory('bloodlineSource', [function() {
	var map = [];
	var i = -1;
	//Assassamite
	map[++i] = {"name": "None", "id": i};
	map[++i] = {"name": "Vizer", "id": i};
	map[++i] = {"name": "Sorcerer", "id": i};
	
	//Brujah
    map[++i] = {"name": "None", "id": i}; 
    map[++i] = {"name": "True Brujah", "id": i};
     
    //Follower of Set
    map[++i] = {"name": "None", "id": i}; 
    map[++i] = {"name": "Tlaclque", "id": i}; 
    map[++i] = {"name": "Viper", "id": i};
     
    //Gangrel
    map[++i] = {"name": "None", "id": i}; 
    map[++i] = {"name": "Coyote", "id": i}; 
    map[++i] = {"name": "Noiad", "id": i}; 
    map[++i] = {"name": "Ahrimane", "id": i};
    
    //Giovanni
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Premascine", "id": i};
    
    //Lasombra
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Kisayd", "id": i};
    
    //Malkavian
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Ananke", "id": i};
    map[++i] = {"name": "Knight of the Moon", "id": i};
    
    //Nosferatu
    map[++i] = {"name": "None", "id": i};
    
    //Toreador
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Ishtarri", "id": i};
    map[++i] = {"name": "Volgirre", "id": i};
    
    //Tremere
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Telyav", "id": i};
    
    //Tzimisce
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Carpathian", "id": i};
    map[++i] = {"name": "Koldun", "id": i}; 
    
    //Ventrue
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Crusader", "id": i};
    
    //Catiff
    map[++i] = {"name": "None", "id": i}; 
    
    //Baali
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Angellis Ater", "id": i};
    
    //Cappadocian
    map[++i] = {"name": "None", "id": i};
    map[++i] = {"name": "Samedi", "id": i};
    map[++i] = {"name": "Lamia", "id": i};
    
    //Ravnos
    map[++i] = {"name": "None", "id": i};                  
    map[++i] = {"name": "Brahman", "id": i};                  
    
    //Salubri
    map[++i] = {"name": "None", "id": i};                  
    map[++i] = {"name": "Healer", "id": i};                  
    
    //Daughter of Cacophony
    map[++i] = {"name": "None", "id": i};                  
    
    //Gargoyle
    map[++i] = {"name": "None", "id": i};          
		
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('clanSource', ['bloodlineSource', function(bloodlineSource) {
	var map = [];
	var i = -1;
	var bi = 0;
	var bloodlines = bloodlineSource.get();
    map[++i] = {"name": "Assamite", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Brujah", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Follower of Set", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Gangrel", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++], bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Giovanni", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Lasombra", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Malkavian", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Nosferatu", "id": i, "bloodlines": [bloodlines[bi++]]};
    map[++i] = {"name": "Toreador", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name": "Tremere", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name":  "Tzimisce", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name":  "Ventrue", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name":  "Catiff", "id": i, "bloodlines": [bloodlines[bi++]]};
    map[++i] = {"name":  "Baali", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name":  "Cappadocian", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name":  "Ravnos", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name":  "Salubri", "id": i, "bloodlines": [bloodlines[bi++], bloodlines[bi++]]};
    map[++i] = {"name":  "Daughter of Cacophony", "id": i, "bloodlines": [bloodlines[bi++]]};
    map[++i] = {"name":  "Gargoyle", "id": i, "bloodlines": [bloodlines[bi++]]};
		
	return {
		get: function() {
			return map;
		}
	}
}]);