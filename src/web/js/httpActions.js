angular.module('services.http', []).
	factory('httpAction', ['$http', function($http) {
		function getUrl(url, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			var config = {'headers': headers};
			$http.get(url, config); 
		},
		function deleteUrl(url, id, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			var config = {'headers': headers};
			$http.delete(url + id, config); 
		};
	}]);