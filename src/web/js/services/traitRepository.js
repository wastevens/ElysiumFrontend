angular.module('services.traits', ['ngResource', 'services.csrfResource']).
factory('traitsRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/traits/',
		getVampireTraits: function(traitType, successFunction) {
			var resource = $resource(this.url + "vampire/:traitType", {traitType: '@traitType'});
			return resource.query({traitType: traitType}, successFunction);
		}
	};
}]);