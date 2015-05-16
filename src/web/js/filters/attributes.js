angular.module('filters.attributes.focuses', []).
filter('physical_attribute_focus', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['PhysicalAttributeFocus'][input];
	};
}]).
filter('mental_attribute_focus', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['MentalAttributeFocus'][input];
	};
}]).
filter('social_attribute_focus', [function() {
	return function(input) {
		return displayableValues['en_US']['Vampire']['SocialAttributeFocus'][input];
	};
}]);