angular.module('filters.attributes.focuses', []).
filter('physical_attribute_focus', [function() {
	return function(input) {
		return displayableValues['en_US']['Character']['PhysicalAttributeFocus'][input.id];
	};
}]).
filter('mental_attribute_focus', [function() {
	return function(input) {
		return displayableValues['en_US']['Character']['MentalAttributeFocus'][input.id];
	};
}]).
filter('social_attribute_focus', [function() {
	return function(input) {
		return displayableValues['en_US']['Character']['SocialAttributeFocus'][input.id];
	};
}]);