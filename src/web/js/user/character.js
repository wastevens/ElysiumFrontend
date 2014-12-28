angular.module('user.character.services', ['ngResource', 'services.csrfResource']).
factory('CharacterRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/user/characters',
		getCharacters: function() {
			return $resource(this.url, {}).query();
		},
		addCharacter: function(userId, characterToPost, csrfHeader, csrfToken) {
			return csrfResource.post(this.url, characterToPost, csrfHeader, csrfToken);
		}
	};
}]);

angular.module('user.character.controllers', ['user.character.services', 'sources.settings']).
controller('addCharacter', ['$scope', '$rootScope', 'TroupeRepository', 'settingsSource', function($scope, $rootScope, TroupeRepository, settingsSource) {
	$scope.settings = [];
	for(var i = 0; i< settingsSource.get().length; i++) {
		$scope.settings[i] = {label: settingsSource.get()[i], value: i};
	}
 	$scope.setting = $scope.settings[0];
	$scope.submit = function(userId, csrfHeader, csrfToken) {
		TroupeRepository.addTroupe({'name': $scope.name, 'setting': $scope.setting.value}, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {$rootScope.$broadcast('troupesUpdated')}).
			error(function(data, status, headers, config) {console.log("addTroupe failed")});
	};
}]);

angular.module('user.character.directives', ['user.character.services']).
directive('listCharacters', ['CharacterRepository', function(CharacterRepository) {
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		link: function(scope, iElement, iAttrs) {
			scope.characters = CharacterRepository.getCharacters();
			scope.$on('charactersUpdated', function(event) {
				scope.characters = CharacterRepository.getCharacters();  
			});
		},
		templateUrl: '/js/user/character/display.html'
	};
}]);

angular.module('user.character.filters', ['filters.setting']);

angular.module('user.character', ['user.character.filters', 'user.character.controllers', 'user.character.directives', 'user.character.services']);