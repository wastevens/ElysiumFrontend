angular.module('services.csrfResource', []).
factory('csrfResource', ['$http', function($http) {
	return {
		delete: function(url, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			var config = {'headers': headers};
			return $http.delete(url, config); 
		}
	}
}]);