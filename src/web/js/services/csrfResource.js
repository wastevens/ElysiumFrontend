angular.module('services.csrfResource', []).
factory('csrfResource', ['$http', function($http) {
	return {
		post: function(url, body, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			return $http.post(url, body, {'headers': headers}); 
		},
		delete: function(url, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			return $http.delete(url, {'headers': headers}); 
		}
	}
}]);