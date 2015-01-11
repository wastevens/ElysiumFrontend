angular.module('user.character.manage.services', ['ngResource', 'services.redirection', 'services.csrfResource', 'services.troupes', 'services.characters']);

angular.module('user.character.manage.controllers', ['user.character.manage.services', 'sources.settings', 'sources.clans']).
controller('manageCharacter', ['$scope', '$rootScope', 'redirect', 'characterRepository', 'clanSource', 'bloodlineSource', 'disciplineSource', function($scope, $rootScope, redirect, characterRepository, clanSource, bloodlineSource, disciplineSource) {
	//--------------------------------------------
	// Setup
	//--------------------------------------------
	$scope.requests = [];
	
	$scope.clans = {
        list: clanSource.get(),
		clan: clanSource.get()[$scope.character.clan]
	}
	
	$scope.bloodlines = [];
	$scope.disciplineOptions = [];
	$scope.inClanDisciplines = [];
	
	$scope.clan = $scope.clans[$scope.character.clan];
	if(!isNaN($scope.character.clan)) {
		$scope.bloodlines = $scope.clans.clan.bloodlines
	}
	
	if(!isNaN($scope.character.bloodline)) {
		$scope.bloodline = bloodlineSource.get()[$scope.character.bloodline];
		$scope.disciplineOptions = $scope.bloodline.disciplines;
	}

	if($scope.character.inClanDisciplines) {
		$scope.character.inClanDisciplines.forEach(function(discipline, index, array) {
			$scope.disciplineOptions[index].inClanDiscipline  = disciplineSource.get()[discipline];
		});
	}
	
	//----------------------------------------------
	
	$scope.clanChange = function() {
		$scope.bloodline = null;
		$scope.bloodlines = [];
		if($scope.clans.clan) {
			$scope.requests.push({"trait": 0, "value": $scope.clans.clan.id});
			$scope.bloodlines = $scope.clans.clan.bloodlines;
			if($scope.bloodlines.length == 1) {
				$scope.bloodline = $scope.bloodlines[0];
			}
		}
		$scope.bloodlineChange();
	}
	$scope.bloodlineChange = function() {
		if($scope.bloodline) {
			$scope.requests.push({"trait": 1, "value": $scope.bloodline.id});
			
			$scope.disciplineOptions.forEach(function(disciplines, index, array) {
				if(disciplines.inClanDiscipline) {
					$scope.requests.push({"trait": 3, "value": disciplines.inClanDiscipline.id});
				}
			});
			
			$scope.disciplineOptions = $scope.bloodline.disciplines;
			$scope.disciplineOptions.forEach(function(disciplines, index, array) {
				if(disciplines.length == 1) {
					$scope.disciplineOptions[index].inClanDiscipline = disciplines[0];
					$scope.disciplineChange(index);
				}
			});
		}
	}
	$scope.disciplineChange = function(index) {
		if($scope.disciplineOptions[index].inClanDiscipline) {
			$scope.requests.push({"trait": 2, "value": $scope.disciplineOptions[index].inClanDiscipline.id});
		}
	}
	$scope.submit = function(csrfHeader, csrfToken) {
		characterRepository.addRequestsToCharacter($scope.character.id, $scope.requests, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {redirect.toUrl('/user/page/characters')}).
			error(function(data, status, headers, config) {console.log("addRequestsToCharacter failed")});
	};
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
}]).
directive('selectClan', [function() {
	return {
		restrict: 'E',
		scope: {
			clans: '=',
			change: '&change'
		},
		templateUrl: '/js/user/character/selectClan.html'
	};
}]).
directive('selectInClanDiscipline', [function() {
	return {
		restrict: 'E',
		scope: {
			disciplines: '=',
			disciplineChange: '&disciplineChange'
		},
		templateUrl: '/js/user/character/inClanDiscipline.html'
	};
}]);

angular.module('user.character.manage.filters', ['filters.setting']);

angular.module('user.character.manage', ['user.character.manage.filters', 'user.character.manage.controllers', 'user.character.manage.directives', 'user.character.manage.services']);