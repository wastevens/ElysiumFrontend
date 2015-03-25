angular.module('services.characters', ['ngResource', 'configuration.authorization']).
factory('characterRepository', ['$resource', '$http', function($resource, $http) {
	return {
		url: '/characters',
		getCharacters: function() {
			return $resource(this.url, {}).query();
		},
		addCharacter: function(characterToPost) {
			return $http.post(this.url, characterToPost);
		},
		addRequestsToCharacter: function(characterId, requests) {
			return $http.post(this.url + "/" + characterId, requests);
		}
	};
}]);
