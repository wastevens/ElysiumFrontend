angular.module('sources.rituals.necromantic', ['services.traits']).
factory('necromanticRitualSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("necromanticRituals");
	return {
		get: function() {
			return map;
		}
	}
}]);