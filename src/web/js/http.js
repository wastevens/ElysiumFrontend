angular.module('controllers.http', []).
	controller('deleteUrl', ['$scope', '$http', function($scope, $http) {
		$scope.deleteUrl = function(url, id, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			var config = {'headers': headers};
			$http.delete(url + id, config); 
		};
	}]);