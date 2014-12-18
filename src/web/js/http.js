angular.module('controllers.http', []).
	controller('deleteUrl', ['$scope', '$http', function($scope, $http) {
		$scope.deleteUrl = function(url, id, csrfHeader, csrfToken) {
			var headers = {};
			headers[csrfHeader] = csrfToken;
			var config = {'headers': headers};
			$http.delete(url + id, config); 
		};
	}]).
	controller('getUrl', ['$scope', '$http', 'Troupes', function($scope, $http, Troupes) {
		console.log("getUrl");
		$scope.troupes = Troupes.query();
		console.log($scope.log);
	}]);