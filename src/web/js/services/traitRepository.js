angular.module('services.traits', ['ngResource']).
factory('traitsRepository', ['$resource', function($resource) {
	return {
		url: '/traits/',
		getVampireTraits: function(traitType, successFunction) {
			var resource = $resource(this.url + "vampire/:traitType", {traitType: '@traitType'});
			return resource.query({traitType: traitType}, successFunction).$promise;
		}
	};
}]);