angular.module('user.character.manage.services', ['ngResource', 'services.redirection', 'services.csrfResource', 'services.troupes', 'services.characters']);

angular.module('user.character.manage.controllers', ['user.character.manage.services', 'sources.settings', 'sources.clans']).
controller('manageCharacter', ['$scope', '$rootScope', 'redirect', 'characterRepository', 'clanSource', 'bloodlineSource', 'disciplineSource', function($scope, $rootScope, redirect, characterRepository, clanSource, bloodlineSource, disciplineSource) {
	//--------------------------------------------
	// Setup
	//--------------------------------------------
	$scope.requests = [];
	
	$scope.disciplineOptions = [];
	
	$scope.clans = {
        list: clanSource.get(),
		clan: clanSource.get()[$scope.character.clan]
	}
	
	$scope.bloodlines = {
	    list: [],
	    bloodline: null
	}
	
	if(!isNaN($scope.character.clan)) {
		$scope.bloodlines.list = $scope.clans.clan.bloodlines;
	}
	
	if(!isNaN($scope.character.bloodline)) {
		$scope.bloodlines.bloodline = bloodlineSource.get()[$scope.character.bloodline];
		$scope.disciplineOptions = $scope.bloodlines.bloodline.disciplines;
	}

	if($scope.character.inClanDisciplines) {
		$scope.character.inClanDisciplines.forEach(function(discipline, index, array) {
			$scope.disciplineOptions[index].discipline  = disciplineSource.get()[discipline];
		});
	}
	
	//----------------------------------------------
	
	$scope.clanChange = function() {
		if($scope.clans.clan) {
			$scope.requests.push({"trait": 0, "value": $scope.clans.clan.id});
			$scope.bloodlines.list = $scope.clans.clan.bloodlines;
			$scope.bloodlines.bloodline = null;
			if($scope.bloodlines.list.length == 1) {
				$scope.bloodlines.bloodline = $scope.bloodlines.list[0];
			}
		}
		$scope.bloodlineChange();
	}
	
	$scope.bloodlineChange = function() {
		if($scope.bloodlines.bloodline) {
			$scope.requests.push({"trait": 1, "value": $scope.bloodlines.bloodline.id});
			
			$scope.disciplineOptions.forEach(function(disciplines, index, array) {
				if(disciplines.discipline) {
					$scope.requests.push({"trait": 3, "value": disciplines.discipline.id});
				}
			});
			
			$scope.disciplineOptions = $scope.bloodlines.bloodline.disciplines;
			$scope.disciplineOptions.forEach(function(disciplines, index, array) {
				$scope.disciplineOptions[index].discipline = null;
				if(disciplines.length == 1) {
					$scope.disciplineOptions[index].discipline = disciplines[0];
				}
				$scope.disciplineChange(index);
			});
		}
	}
	$scope.disciplineChange = function(index) {
		if($scope.disciplineOptions[index].discipline) {
			$scope.requests.push({"trait": 2, "value": $scope.disciplineOptions[index].discipline.id});
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
directive('selectBloodline', [function() {
	return {
		restrict: 'E',
		scope: {
			bloodlines: '=',
			change: '&change'
		},
		templateUrl: '/js/user/character/selectBloodline.html'
	};
}]).
directive('selectInClanDiscipline', [function() {
	return {
		restrict: 'E',
		scope: {
			disciplines: '=',
			change: '&change'
		},
		templateUrl: '/js/user/character/inClanDiscipline.html'
	};
}]);

angular.module('user.character.manage.filters', ['filters.setting']);

angular.module('user.character.manage', ['user.character.manage.filters', 'user.character.manage.controllers', 'user.character.manage.directives', 'user.character.manage.services']);