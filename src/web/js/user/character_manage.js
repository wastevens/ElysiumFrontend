angular.module('user.character.manage.services', ['ngResource', 'services.redirection', 'services.csrfResource', 'services.troupes', 'services.characters']);

var ratings = [{value: 0, display: "Remove"},
               {value: 1, display: "1"},
               {value: 2, display: "2"},
               {value: 3, display: "3"},
               {value: 4, display: "4"},
               {value: 5, display: "5"}];

function chunk(arr, size) {
	var newArr = [];
	for (var i=0; i<arr.length; i+=size) {
		newArr.push(arr.slice(i, i+size));
	}
	return newArr;
}

function copyTrait(traitToCopy) {
	return {
		"name": traitToCopy.name,
		"ordinal": traitToCopy.ordinal,
		"rating": traitToCopy.rating,
		"requiresSpecialization": traitToCopy.requiresSpecialization,
		"specialization": traitToCopy.specialization
	};
}

function displaySkillsInGroups(scope) {
	var numberOfColumns = 3;
	var skillsToDisplay = scope.skills.slice(0);
	while(skillsToDisplay.length % numberOfColumns != 0) {
		skillsToDisplay.push({});
	}
	scope.skillGroups = chunk(skillsToDisplay, skillsToDisplay.length / numberOfColumns);
}

angular.module('user.character.manage.controllers', ['user.character.manage.services', 'sources.settings', 'sources.clans', 'sources.attributes.focuses', 'sources.skills']).
controller('manageCharacter', ['$scope', '$rootScope', 'redirect', 'characterRepository', 'clanSource', 'bloodlineSource', 'disciplineSource', 'physicalFocusSource', 'socialFocusSource', 'mentalFocusSource', 'skillSource',
                       function($scope, $rootScope, redirect, characterRepository, clanSource, bloodlineSource, disciplineSource, physicalFocusSource, socialFocusSource, mentalFocusSource, skillSource) {
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
	
	var attributePriorities = [{priority: "Primary (7)", value: 7}, 
	                           {priority: "Secondary (5)", value: 5}, 
	                           {priority: "Tertiary (3)", value: 3}];
	
	var attributePriority = [];
	attributePriority[3] = attributePriorities[2];
	attributePriority[5] = attributePriorities[1];
	attributePriority[7] = attributePriorities[0];
	
	$scope.physicalAttributeFocuses = $scope.character.physicalAttributeFocuses;
	$scope.socialAttributeFocuses = $scope.character.socialAttributeFocuses;
	$scope.mentalAttributeFocuses = $scope.character.mentalAttributeFocuses;
	
	$scope.physical = {
		priorities: attributePriorities,
	    value: $scope.character.physicalAttribute,
	    priority: attributePriority[$scope.character.physicalAttribute],
	    focuses: physicalFocusSource.get(),
	    focus: physicalFocusSource.get()[$scope.character.physicalAttributeFocuses[0]]
	}
	
	$scope.social = {
		priorities: attributePriorities,
		value: $scope.character.socialAttribute,
		priority: attributePriority[$scope.character.socialAttribute],
		focuses: socialFocusSource.get(),
	    focus: socialFocusSource.get()[$scope.character.socialAttributeFocuses[0]]
	}
	
	$scope.mental = {
		priorities: attributePriorities,
		value: $scope.character.mentalAttribute,
		priority: attributePriority[$scope.character.mentalAttribute],
		focuses: mentalFocusSource.get(),
	    focus: mentalFocusSource.get()[$scope.character.mentalAttributeFocuses[0]]
	}
	
	$scope.skills = [];
	skillSource.get().forEach(function(skill, index, array) {
		$scope.skills.push(skill);
	});
	
	$scope.character.skills.forEach(function(characterSkill, index, array){
		for(var i=0;i<$scope.skills.length;i++) {
			if($scope.skills[i].ordinal == characterSkill.ordinal) {
				if($scope.skills[i].requiresSpecialization) {
					$scope.skills.splice(i, 0, copyTrait($scope.skills[i]));
					$scope.skills[i].specialization = characterSkill.specialization;
				}
				$scope.skills[i].rating = ratings[characterSkill.rating];
				break;
			}
		}
	});
	displaySkillsInGroups($scope);
	
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
		$scope.disciplineOptions.forEach(function(disciplines, index, array) {
			if(disciplines.discipline) {
				$scope.requests.push({"trait": 3, "value": disciplines.discipline.id});
			}
		});
		
		if($scope.bloodlines.bloodline) {
			$scope.requests.push({"trait": 1, "value": $scope.bloodlines.bloodline.id});
			$scope.disciplineOptions = $scope.bloodlines.bloodline.disciplines;
			$scope.disciplineOptions.forEach(function(disciplines, index, array) {
				$scope.disciplineOptions[index].discipline = null;
				if(disciplines.length == 1) {
					$scope.disciplineOptions[index].discipline = disciplines[0];
					$scope.disciplineChange(index);
				}
			});
		} else {
			$scope.disciplineOptions = [];
		}
	}
	$scope.disciplineChange = function(index) {
		if($scope.disciplineOptions[index].discipline) {
			$scope.requests.push({"trait": 2, "value": $scope.disciplineOptions[index].discipline.id});
		}
	}
	
	$scope.physicalChange = function() {
		if($scope.physical.priority.value) {
			$scope.requests.push({"trait": 4, "value": $scope.physical.priority.value});
		}
	}
	
	$scope.socialChange = function() {
		if($scope.social.priority.value) {
			$scope.requests.push({"trait": 5, "value": $scope.social.priority.value});
		}
	}
	
	$scope.mentalChange = function() {
		if($scope.mental.priority.value) {
			$scope.requests.push({"trait": 6, "value": $scope.mental.priority.value});
		}
	}
	
	$scope.physicalFocusChange = function() {
		if($scope.physicalAttributeFocuses) {
			$scope.physicalAttributeFocuses.forEach(function(focus, index, array) {
				$scope.requests.push({"trait": 10, "value": focus});	
			});
			$scope.physicalAttributeFocuses = [];
		}
		if($scope.physical.focus) {
			$scope.requests.push({"trait": 7, "value": $scope.physical.focus.id});
		}
	}
	
	$scope.socialFocusChange = function() {
		if($scope.socialAttributeFocuses) {
			$scope.socialAttributeFocuses.forEach(function(focus, index, array) {
				$scope.requests.push({"trait": 11, "value": focus});	
			});
			$scope.socialAttributeFocuses = [];
		}
		if($scope.social.focus) {
			$scope.requests.push({"trait": 8, "value": $scope.social.focus.id});
		}
	}
	
	$scope.mentalFocusChange = function() {
		if($scope.mentalAttributeFocuses) {
			$scope.mentalAttributeFocuses.forEach(function(focus, index, array) {
				$scope.requests.push({"trait": 12, "value": focus});	
			});
			$scope.mentalAttributeFocuses = [];
		}
		if($scope.mental.focus) {
			$scope.requests.push({"trait": 9, "value": $scope.mental.focus.id});
		}
	}
	
	$scope.skillChange = function(groupIndex, skillIndex) {
		var skill = $scope.skillGroups[groupIndex][skillIndex];
		
		if(skill.rating.value == 0) {
			$scope.removeSkill(skill);
		} else {
			$scope.setSkill(skill);
		}
		
		displaySkillsInGroups($scope);
	}
	
	$scope.setSkill = function(skill) {
		$scope.requests.push({"trait": 13, "value": skill.ordinal, "rating": skill.rating.value, "specialization": skill.specialization});
		if(skill.specialization) {
			for(var i=0;i<$scope.skills.length;i++) {
				if($scope.skills[i].ordinal == skill.ordinal && $scope.skills[i].specialization == skill.specialization) {
					$scope.skills.splice(i+1, 0, copyTrait(skill));
					$scope.skills[i+1].rating = "";
					$scope.skills[i+1].specialization = "";
					break;
				}
			}
		}
	}
	
	$scope.removeSkill = function(skill) {
		$scope.requests.push({"trait": 14, "value": skill.ordinal, "specialization": skill.specialization});
		if(skill.specialization) {
			for(var i=0;i<$scope.skills.length;i++) {
				if($scope.skills[i].ordinal == skill.ordinal && $scope.skills[i].specialization == skill.specialization) {
					$scope.skills.splice(i, 1);
					break;
				}
			}
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
}]).
directive('selectAttribute', [function() {
	return {
		restrict: 'E',
		scope: {
			attributes: '=',
			change: '&change'
		},
		templateUrl: '/js/user/character/selectAttributeValue.html'
	};
}]).
directive('selectAttributeFocus', [function() {
	return {
		restrict: 'E',
		scope: {
			attributes: '=',
			change: '&change'
		},
		templateUrl: '/js/user/character/selectAttributeFocus.html'
	};
}]).
directive('selectSkill', [function() {
	return {
		restrict: 'E',
		scope: {
			skillgroups: '=',
			change: '&change'
		},
		link: function (scope) {
		      scope.ratings = ratings;
	    },
		templateUrl: '/js/user/character/selectSkill.html'
	};
}]);

angular.module('user.character.manage.filters', ['filters.setting']);

angular.module('user.character.manage', ['user.character.manage.filters', 'user.character.manage.controllers', 'user.character.manage.directives', 'user.character.manage.services']);