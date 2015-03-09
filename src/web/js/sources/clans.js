angular.module('sources.clans', ['sources.disciplines']).
factory('bloodlineSource', ['disciplineSource', function(disciplineSource) {
	var disciplines = disciplineSource.get();
	var map = [];
	var i = -1;
	//Thaumaturgy: [disciplines[27], disciplines[28], disciplines[29], disciplines[30], disciplines[31], disciplines[32], disciplines[33], disciplines[34]]
	//Necromancy: [disciplines[23], disciplines[24], disciplines[25], disciplines[36]]
	
	//Assassamite 0
	map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[2]],[disciplines[11]],[disciplines[16]]]};
	map[++i] = {"name": "Vizer", "id": i, "disciplines": [[disciplines[1]],[disciplines[2]],[disciplines[16]]]};
	map[++i] = {"name": "Sorcerer", "id": i, "disciplines": [[disciplines[11]],[disciplines[16]],[disciplines[31]], [disciplines[27], disciplines[28], disciplines[29], disciplines[30], disciplines[32], disciplines[33], disciplines[34]] ]};
	
	//Brujah 1
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[2]],[disciplines[13]],[disciplines[14]]]}; 
    map[++i] = {"name": "True Brujah", "id": i, "disciplines": [[disciplines[13]],[disciplines[14]],[disciplines[18]]]};
     
    //Follower of Set 2 
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[11]],[disciplines[14]],[disciplines[17]]]}; 
    map[++i] = {"name": "Tlaclque", "id": i, "disciplines": [[disciplines[11]],[disciplines[14]],[disciplines[15]]]}; 
    map[++i] = {"name": "Viper", "id": i, "disciplines": [[disciplines[13]],[disciplines[14]],[disciplines[17]]]};
     
    //Gangrel 3
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[0]],[disciplines[7]],[disciplines[15]]]}; 
    map[++i] = {"name": "Coyote", "id": i, "disciplines": [[disciplines[2]],[disciplines[11]],[disciplines[15]]]}; 
    map[++i] = {"name": "Noiad", "id": i, "disciplines": [[disciplines[0]],[disciplines[1]],[disciplines[15]]]}; 
    map[++i] = {"name": "Ahrimane", "id": i, "disciplines": [[disciplines[0]],[disciplines[14]],[disciplines[30]]]};
    
    //Giovanni 4
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[6]],[disciplines[13]],[disciplines[23]]]};
    map[++i] = {"name": "Premascine", "id": i, "disciplines": [[disciplines[6]],[disciplines[13]],[disciplines[26]]]};
    
    //Lasombra 5
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[6]],[disciplines[12]],[disciplines[13]]]};
    map[++i] = {"name": "Kisayd", "id": i, "disciplines": [[disciplines[6]],[disciplines[9]],[disciplines[12]]]};
    
    //Malkavian 6 
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[1]],[disciplines[5]],[disciplines[11]]]};
    map[++i] = {"name": "Ananke", "id": i, "disciplines": [[disciplines[1]],[disciplines[5]],[disciplines[14]]]};
    map[++i] = {"name": "Knight of the Moon", "id": i, "disciplines": [[disciplines[1]],[disciplines[6]],[disciplines[11]]]};
    
    //Nosferatu 7
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[0]],[disciplines[11]],[disciplines[13]]]};
    
    //Toreador 8
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[1]],[disciplines[2]],[disciplines[14]]]};
    map[++i] = {"name": "Ishtarri", "id": i, "disciplines": [[disciplines[1]],[disciplines[7]],[disciplines[14]]]};
    map[++i] = {"name": "Volgirre", "id": i, "disciplines": [[disciplines[1]],[disciplines[2]],[disciplines[14]]]};
    
    //Tremere 9
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[1]],[disciplines[6]],[disciplines[27]]]};
    map[++i] = {"name": "Telyav", "id": i, "disciplines": [[disciplines[1]],[disciplines[14]],[disciplines[27]]]};
    
    //Tzimisce 10
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[0]],[disciplines[1]],[disciplines[21]]]};
    map[++i] = {"name": "Carpathian", "id": i, "disciplines": [[disciplines[0]],[disciplines[1]],[disciplines[6]]]};
    map[++i] = {"name": "Koldun", "id": i, "disciplines": [[disciplines[0]],[disciplines[1]],[disciplines[30]], [disciplines[27], disciplines[28], disciplines[29], disciplines[31], disciplines[32], disciplines[33], disciplines[34]]]}; 
    
    //Ventrue 11
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[6]],[disciplines[7]],[disciplines[14]]]};
    map[++i] = {"name": "Crusader", "id": i, "disciplines": [[disciplines[1]],[disciplines[6]],[disciplines[14]]]};
    
    //Catiff 12
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[0], disciplines[1], disciplines[2], disciplines[6], disciplines[7], disciplines[11], disciplines[13], disciplines[14]],
                                                         [disciplines[0], disciplines[1], disciplines[2], disciplines[6], disciplines[7], disciplines[11], disciplines[13], disciplines[14]],
                                                         [disciplines[0], disciplines[1], disciplines[2], disciplines[6], disciplines[7], disciplines[11], disciplines[13], disciplines[14]]]}; 
    
    //Baali 13
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[4]],[disciplines[11]],[disciplines[14]]]};
    map[++i] = {"name": "Angellis Ater", "id": i, "disciplines": [[disciplines[4]],[disciplines[5]],[disciplines[11], disciplines[13], disciplines[14]]]};
    
    //Cappadocian 14
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[1]],[disciplines[7]],[disciplines[26]]]};
    map[++i] = {"name": "Samedi", "id": i, "disciplines": [[disciplines[7]],[disciplines[11]],[disciplines[19]]]};
    map[++i] = {"name": "Lamia", "id": i, "disciplines": [[disciplines[7]],[disciplines[13]],[disciplines[26]]]};
    
    //Ravnos 15
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[0]],[disciplines[3]],[disciplines[7]]]};                  
    map[++i] = {"name": "Brahman", "id": i, "disciplines": [[disciplines[0]],[disciplines[1]],[disciplines[3]]]};                  
    
    //Salubri 16
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[1]],[disciplines[7]],[disciplines[20]]]};                  
    map[++i] = {"name": "Healer", "id": i, "disciplines": [[disciplines[1]],[disciplines[7]],[disciplines[10]]]};                  
    
    //Daughter of Cacophony 17
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[7]],[disciplines[8]],[disciplines[14]]]};                  
    
    //Gargoyle 18
    map[++i] = {"name": "None", "id": i, "disciplines": [[disciplines[7]],[disciplines[13]],[disciplines[22]]]};          
		
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