angular.module('user.character.creation.services', ['ngResource', 'services.redirection', 'services.troupes', 'services.characters']);

angular.module('user.character.creation.controllers', ['user.character.creation.services', 'sources.vampire', 'filters.vampire']).
controller('addCharacter', ['$scope', '$rootScope', 'redirect', 'characterRepository', 'troupeRepository', 'settingSource', 
                    function($scope, $rootScope, redirect, characterRepository, troupeRepository, settingSource) {
	$scope.troupes = troupeRepository.getTroupes();
 	$scope.troupe = $scope.troupes[0];
 	settingSource.get().then(function(settings){
 		$scope.settings = settings;
 		$scope.setting = $scope.settings[0];
 	});
	$scope.submit = function() {
		characterRepository.addCharacter({'name': $scope.name, 'troupeId': $scope.troupe.id, 'settingId': $scope.setting.id}).
			success(function(data, status, headers, config) {redirect.toUrl('/user/page/characters')}).
			error(function(data, status, headers, config) {console.log("addCharacter failed")});
	};
}]);

angular.module('user.character.creation.directives', ['user.character.creation.services']).
directive('createCharacter', ['troupeRepository', function(troupeRepository) {
	return {
		restrict: 'E',
		templateUrl: '/js/user/character/create.html'
	};
}]);

angular.module('user.character.creation.filters', []);

angular.module('user.character.creation', ['user.character.creation.filters', 'user.character.creation.controllers', 'user.character.creation.directives', 'user.character.creation.services']);