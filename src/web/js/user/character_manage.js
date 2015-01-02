angular.module('user.character.manage.services', ['ngResource', 'services.redirection', 'services.csrfResource', 'services.troupes', 'services.characters']);

angular.module('user.character.manage.controllers', ['user.character.manage.services', 'sources.settings']).
controller('manageCharacter', ['$scope', '$rootScope', 'redirect', 'characterRepository', function($scope, $rootScope, redirect, characterRepository) {
//	$scope.submit = function(csrfHeader, csrfToken) {
//		characterRepository.addCharacter({'name': $scope.name, 'troupeId': $scope.troupe.id}, csrfHeader, csrfToken).
//			success(function(data, status, headers, config) {redirect.toUrl('/user/page/characters')}).
//			error(function(data, status, headers, config) {console.log("addCharacter failed")});
//	};
}]);

angular.module('user.character.manage.directives', ['user.character.manage.services']).
directive('manageCharacter', ['characterRepository', function(characterRepository) {
	return {
		restrict: 'E',
		scope: {
			character: '=',
			csrf: '='
		},
		templateUrl: '/js/user/character/manage.html'
	};
}]);

angular.module('user.character.manage.filters', ['filters.setting']);

angular.module('user.character.manage', ['user.character.manage.filters', 'user.character.manage.controllers', 'user.character.manage.directives', 'user.character.manage.services']);