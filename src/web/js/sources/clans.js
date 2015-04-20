angular.module('sources.clans', ['sources.disciplines']).
factory('bloodlineSource', ['traitsRepository', function(traitsRepository) {
	var map = traitsRepository.getVampireTraits("bloodlines");
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
}]);