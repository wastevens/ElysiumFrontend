angular.module('filters.vampire', []).
filter('venue', [function() {
	return function(input) {
		return venues[input.id];
	};
}]).
filter('setting', [function() {
	return function(input) {
		return settings[input.id];
	};
}]).
filter('clan', [function() {
	return function(input) {
		return clan[input.id];
	};
}]).
filter('bloodline', [function() {
	return function(input) {
		return bloodline[input.id];
	};
}]).
filter('discipline', [function() {
	return function(input) {
		return discipline[input.id];
	};
}]).
filter('elder_power', [function() {
	return function(input) {
		return elderPower[input.id];
	};
}]).
filter('technique', [function() {
	return function(input) {
		return technique[input.id];
	};
}]).
filter('thaumaturgical_ritual', [function() {
	return function(input) {
		return thaumaturgicalRitual[input.id];
	};
}]).
filter('necromantic_ritual', [function() {
	return function(input) {
		return necromanticRitual[input.id];
	};
}]).
filter('background', [function() {
	return function(input) {
		return background[input.id];
	};
}]).
filter('skill', [function() {
	return function(input) {
		return skill[input.id];
	};
}]).
filter('merit', [function() {
	return function(input) {
		return merit[input.id];
	};
}]).
filter('flaw', [function() {
	return function(input) {
		return flaw[input.id];
	};
}]);
