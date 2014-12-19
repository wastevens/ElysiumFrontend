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
		  console.log("directive heard troupes updated " + event);
		  scope.troupes = scope.troupeSource.query();  
	  });
  },
  templateUrl: '/js/admin/troupes.html'
};
});

angular.module('services.troupes', ['ngResource']).
controller('MyController', ['$rootScope','Troupes', function ($rootScope, Troupes) {
			$rootScope.$on('troupesUpdated', function(event) {console.log("controller heard troupes updated " + event)});
			$rootScope.callNotify = function(msg) {
	   		   $rootScope.$broadcast('troupesUpdated');
	   		   console.log("notify troupes");
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