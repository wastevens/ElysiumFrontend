angular.module('user.patronage.services', ['ngResource']);

angular.module('user.patronage.controllers', []).
controller('viewPatronage', ['$scope', function($scope) {
}]);

angular.module('user.patronage.directives', ['user.patronage.services']).
directive('managePatronage', [function() {
	return {
		restrict: 'E',
		scope: {
			user: '=',
		},
		templateUrl: '/js/user/patronage/display.html'
	};
}]);

angular.module('user.patronage.filters', ['filters.payment_type']);

angular.module('user.patronage', ['user.patronage.filters', 'user.patronage.controllers', 'user.patronage.directives', 'user.patronage.services']);