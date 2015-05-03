angular.module('user.patronage.services', ['ngResource']);

angular.module('user.patronage.controllers', []);

angular.module('user.patronage.directives', ['user.patronage.services']).
directive('managePatronage', [function() {
	return {
		restrict: 'E',
		link: function(scope, iElement, iAttrs) {
		},
		templateUrl: '/js/user/patronage/display.html'
	};
}]);

angular.module('user.patronage.filters', []);

angular.module('user.patronage', ['user.patronage.filters', 'user.patronage.controllers', 'user.patronage.directives', 'user.patronage.services']);