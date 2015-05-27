angular.module('filters.attributes.focuses', []).
filter('physical_attribute_focus', [function() {
	return function(input) {
		return physical[input.id];
	};
}]).
filter('mental_attribute_focus', [function() {
	return function(input) {
		return mental[input.id];
	};
}]).
filter('social_attribute_focus', [function() {
	return function(input) {
		return social[input.id];
	};
}]);