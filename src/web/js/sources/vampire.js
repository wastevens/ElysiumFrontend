angular.module('sources.vampire', []).
factory('settingSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("settings");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('clanSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("clans");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('bloodlineSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("bloodlines");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('disciplineSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("disciplines");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('elderPowerSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("elderPowers");
	return {
		get: function() {
			return map;
		}
	}
}]).
factory('techniqueSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("techniques");
	return {
		get: function() {
			return map;
		}
	}
}]).
factory('thaumaturgicalRitualSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("thaumaturgicalRituals");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('necromanticRitualSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("necromanticRituals");
	return {
		get: function() {
			return map;
		}
	}
}]).
factory('backgroundSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("backgrounds");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('meritSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("merits");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('flawSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("flaws");
	return {
		get: function() {
			return map;
		}
	}	
}]);