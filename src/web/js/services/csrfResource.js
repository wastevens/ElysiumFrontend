angular.module('services.csrfResource', []).
factory('csrfResource', ['$http', '$window', function($http, $window) {
	return {
		get: function(url) {
			var headers = {};
			headers.authorization = $window.sessionStorage.token;
			return $http.get(url); 
		},
		post: function(url, body) {
			var headers = {};
			headers.authorization = $window.sessionStorage.token;
			return $http.post(url, body); 
		},
		put: function(url, body) {
			var headers = {};
			headers.authorization = $window.sessionStorage.token;
			return $http.put(url, body); 
		},
		delete: function(url) {
			var headers = {};
			headers.authorization = $window.sessionStorage.token;
			return $http.delete(url); 
		}
	}
}]);