angular.module('filters.vampire', []).
filter('setting', [function() {
	return function(input) {
		return settings[input.id];
	};
}]).
filter('clan', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Clan'][input.id];
	};
}]).
filter('bloodline', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Bloodline'][input.id];
	};
}]).
filter('discipline', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Discipline'][input.id];
	};
}]).
filter('elder_power', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['ElderPower'][input.id];
	};
}]).
filter('technique', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Technique'][input.id];
	};
}]).
filter('thaumaturgical_ritual', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['ThaumaturgicalRitual'][input.id];
	};
}]).
filter('necromantic_ritual', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['NecromanticRitual'][input.id];
	};
}]).
filter('background', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Background'][input.id];
	};
}]).
filter('merit', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Merit'][input.id];
	};
}]).
filter('flaw', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['Flaw'][input.id];
	};
}]);
