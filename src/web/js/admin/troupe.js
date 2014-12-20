angular.module('admin.troupe.services', ['ngResource', 'services.csrfResource']).
factory('TroupeRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/admin/troupes',
		getTroupes: function() {
			return $resource(this.url, {}).query();
		},
		deleteTroupe: function(id, csrfHeader, csrfToken) {
			return csrfResource.delete(this.url + '/' + id, csrfHeader, csrfToken);
		}
	};
}]);

angular.module('admin.troupe.controllers', ['admin.troupe.services']).
controller('deleteTroupe', ['$scope', '$rootScope', 'TroupeRepository', function($scope, $rootScope, TroupeRepository) {
	$scope.deleteTroupe = function(id, csrfHeader, csrfToken) {
		TroupeRepository.deleteTroupe(id, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {$rootScope.$broadcast('troupesUpdated')}).
			error(function(data, status, headers, config) {console.log("deleteTroupe failed")});
	};
}]);

angular.module('admin.troupe.directives', ['admin.troupe.services']).
directive('listTroupes', ['TroupeRepository', function(TroupeRepository) {
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		link: function(scope, iElement, iAttrs) {
			scope.troupes = TroupeRepository.getTroupes();
			scope.$on('troupesUpdated', function(event) {
				scope.troupes = TroupeRepository.getTroupes();  
			});
		},
		templateUrl: '/js/admin/troupes.html'
	};
}]);

angular.module('admin.troupe.filters', ['filters.setting']);

angular.module('admin.troupe', ['admin.troupe.filters', 'admin.troupe.controllers', 'admin.troupe.directives', 'admin.troupe.services']);