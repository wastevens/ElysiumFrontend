angular.module('user.character.creation.services', ['ngResource', 'services.redirection', 'services.csrfResource', 'services.troupes', 'services.characters']);

angular.module('user.character.creation.controllers', ['user.character.creation.services', 'sources.settings']).
controller('addCharacter', ['$scope', '$rootScope', 'redirect', 'characterRepository', 'troupeRepository', 'settingsSource', function($scope, $rootScope, redirect, characterRepository, troupeRepository, settingsSource) {
	$scope.troupes = troupeRepository.getTroupes();
 	$scope.troupe = $scope.troupes[0];
	$scope.submit = function(csrfHeader, csrfToken) {
		characterRepository.addCharacter({'name': $scope.name, 'troupeId': $scope.troupe.id}, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {redirect.toUrl('/user/page/characters')}).
			error(function(data, status, headers, config) {console.log("addCharacter failed")});
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