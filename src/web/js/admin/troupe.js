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
	 $scope.troupesSource = Troupes;
  }],
  link: function(scope, iElement, iAttrs, ctrl) {
      scope.troupes = scope.troupesSource.query();
  },
  templateUrl: '/js/admin/troupes.html'
};
});

angular.module('services.troupes', ['ngResource']).
controller('MyController', ['$scope','Troupes', function ($scope, Troupes) {
	   $scope.callNotify = function(msg) {
		   var troupes = Troupes.query();
		   console.log(troupes);
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