angular.module('sources.rituals.thaumaturgical', ['services.traits']).
factory('thaumaturgicalRitualSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("thaumaturgicalRituals");
	return {
		get: function() {
			return map;
		}
	}	
}]);