angular.module('services.characters', ['ngResource', 'services.csrfResource']).
factory('characterRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/characters',
		getCharacters: function() {
			return $resource(this.url, {}).query();
		},
		addCharacter: function(characterToPost) {
			return csrfResource.post(this.url, characterToPost);
		},
		addRequestsToCharacter: function(characterId, requests) {
			return csrfResource.post(this.url + "/" + characterId, requests);
		}
	};
}]);
