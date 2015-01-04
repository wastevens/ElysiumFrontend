angular.module('services.characters', ['ngResource', 'services.csrfResource']).
factory('characterRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/characters',
		getCharacters: function() {
			return $resource(this.url, {}).query();
		},
		addCharacter: function(characterToPost, csrfHeader, csrfToken) {
			return csrfResource.post(this.url, characterToPost, csrfHeader, csrfToken);
		},
		addRequestsToCharacter: function(characterId, requests, csrfHeader, csrfToken) {
			return csrfResource.post(this.url + "/" + characterId, requests, csrfHeader, csrfToken);
		}
	};
}]);
