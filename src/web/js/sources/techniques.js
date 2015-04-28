angular.module('sources.techniques', ['services.traits']).
factory('techniqueSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("techniques");
	return {
		get: function() {
			return map;
		}
	}
}]);