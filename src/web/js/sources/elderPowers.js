angular.module('sources.elderPowers', ['services.traits']).
factory('elderPowerSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("elderPowers");
	return {
		get: function() {
			return map;
		}
	}
}]);