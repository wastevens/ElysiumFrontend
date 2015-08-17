function _filterOn(key, value) {
	return displayableValues['en_US']['Vampire'][key][value];
}

angular.module('filters.vampire', []).
filter('venue', [function() {
	return function(input) {
		return _filterOn('Venue', input);
	};
}]).
filter('setting', [function() {
	return function(input) {
		return _filterOn('Setting', input);
	};
}]).
filter('eventStatus', [function() {
	return function(input) {
		return _filterOn('EventStatus', input);
	};
}]).
filter('clan', [function() {
	return function(input) {
		return _filterOn('Clan', input);
	};
}]).
filter('bloodline', [function() {
	return function(input) {
		return _filterOn('Bloodline', input);
	};
}]).
filter('discipline', [function() {
	return function(input) {
		return _filterOn('Discipline', input);
	};
}]).
filter('elder_power', [function() {
	return function(input) {
		return _filterOn('ElderPower', input);
	};
}]).
filter('technique', [function() {
	return function(input) {
		return _filterOn('Technique', input);
	};
}]).
filter('thaumaturgical_ritual', [function() {
	return function(input) {
		return _filterOn('ThaumaturgicalRitual', input);
	};
}]).
filter('necromantic_ritual', [function() {
	return function(input) {
		return _filterOn('NecromanticRitual', input);
	};
}]).
filter('background', [function() {
	return function(input) {
		return _filterOn('Background', input);
	};
}]).
filter('skill', [function() {
	return function(input) {
		return _filterOn('Skill', input);
	};
}]).
filter('merit', [function() {
	return function(input) {
		return _filterOn('Merit', input);
	};
}]).
filter('flaw', [function() {
	return function(input) {
		return _filterOn('Flaw', input);
	};
}]);
