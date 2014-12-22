angular.module('services.csrfResource', []).
factory('csrfResource', ['$http', function($http) {
	return {
		get: function(url) {
			return $http.get(url); 
		},
		post: function(url, body, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			return $http.post(url, body, {'headers': headers}); 
		},
		put: function(url, body, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			return $http.put(url, body, {'headers': headers}); 
		},
		delete: function(url, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			return $http.delete(url, {'headers': headers}); 
		}
	}
}]);