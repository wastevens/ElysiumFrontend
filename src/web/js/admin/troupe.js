angular.module('admin.troupe.filters', ['filters.setting']);

angular.module('admin.troupe.controllers', ['controllers.http']);

angular.module('directive.troupes', []).
directive('listTroupes', function() {
return {
  restrict: 'E',
  scope: {
    csrf: '='
  },
  controller: ['$scope', 'Troupes', function($scope, Troupes) {
	 $scope.troupeSource = Troupes;
  }],
  link: function(scope, iElement, iAttrs) {
	  scope.troupes = scope.troupeSource.query();
	  scope.$on('troupesUpdated', function(event) {
		  scope.troupes = scope.troupeSource.query();  
	  });
  },
  templateUrl: '/js/admin/troupes.html'
};
});

angular.module('services.troupes', ['ngResource']).
controller('deleteTroupe', ['$scope', '$rootScope', '$http', function($scope, $rootScope, $http) {
	$scope.deleteTroupe = function(url, id, csrfHeader, csrfToken) {
		var headers = {};
		headers[csrfHeader] = csrfToken;
		var config = {'headers': headers};
		$http.delete(url + id, config).
			  success(function(data, status, headers, config) {
				$rootScope.$broadcast('troupesUpdated');
			  }).
			  error(function(data, status, headers, config) {console.log("deleteTroupe failed")});
	};
}]).
factory('Troupes', ['$resource', function($resource){
	return $resource('/admin/troupes/:troupeId', {}, {
		query: {method:'GET', isArray:true}
	});
}]);

angular.module('admin.troupe.directives', ['directive.troupes']);

angular.module('admin.troupe.services', ['services.troupes']);

angular.module('admin.troupe', ['admin.troupe.filters', 'admin.troupe.controllers', 'admin.troupe.directives', 'admin.troupe.services']);