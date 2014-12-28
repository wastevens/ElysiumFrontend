angular.module('user.character.creation.services', ['ngResource', 'services.csrfResource', 'services.troupes', 'services.characters']);

angular.module('user.character.creation.controllers', ['user.character.creation.services', 'sources.settings']).
controller('addCharacter', ['$scope', '$rootScope', 'characterRepository', 'troupeRepository', 'settingsSource', function($scope, $rootScope, characterRepository, troupeRepository, settingsSource) {
	$scope.troupes = troupeRepository.getTroupes();
 	$scope.troupe = $scope.troupes[0];
	$scope.submit = function(csrfHeader, csrfToken) {
		console.log('submit');
	};
}]);

angular.module('user.character.creation.directives', ['user.character.creation.services']).
directive('createCharacter', ['troupeRepository', function(troupeRepository) {
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		templateUrl: '/js/user/character/create.html'
	};
}]);

angular.module('user.character.creation.filters', ['filters.setting']);

angular.module('user.character.creation', ['user.character.creation.filters', 'user.character.creation.controllers', 'user.character.creation.directives', 'user.character.creation.services']);