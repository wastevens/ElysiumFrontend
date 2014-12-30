angular.module('user.character.services', ['ngResource', 'services.csrfResource', 'services.characters']);

angular.module('user.character.controllers', []);

angular.module('user.character.directives', ['user.character.services']).
directive('listCharacters', ['characterRepository', function(characterRepository) {
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		link: function(scope, iElement, iAttrs) {
			scope.characters = characterRepository.getCharacters();
		},
		templateUrl: '/js/user/character/display.html'
	};
}]);

angular.module('user.character.filters', ['filters.setting', 'filters.status.player', 'filters.status.approval']);

angular.module('user.character', ['user.character.filters', 'user.character.controllers', 'user.character.directives', 'user.character.services']);