angular.module('sources.attributes.focuses', ['services.traits']).
factory('physicalFocusSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("physicalattributefocuses");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('mentalFocusSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("mentalattributefocuses");
	return {
		get: function() {
			return map;
		}
	}	
}]).
factory('socialFocusSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("socialattributefocuses");
	return {
		get: function() {
			return map;
		}
	}	
}]);