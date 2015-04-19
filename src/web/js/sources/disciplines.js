angular.module('sources.disciplines', ['services.traits']).
factory('disciplineSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("disciplines");
	return {
		get: function() {
			return map;
		}
	}	
}]);