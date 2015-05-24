angular.module('sources.backgrounds', ['services.traits']).
factory('backgroundSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("backgrounds");
	return {
		get: function() {
			return map;
		}
	}	
}]);