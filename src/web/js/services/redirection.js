angular.module('configuration.redirection', []).
config(function($locationProvider) { $locationProvider.html5Mode(true) });

angular.module('services.redirection', ['configuration.redirection']).
factory('redirect', ['$location', '$window', function($location, $window) {
	return {
		toUrl: function(url) {
			$location.path(url);
			$location.replace();
			$window.location.reload();
		}
	}
}]);