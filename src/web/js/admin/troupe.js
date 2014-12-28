angular.module('admin.troupe.services', ['services.troupes']);

angular.module('admin.troupe.controllers', ['admin.troupe.services', 'sources.settings']).
controller('deleteTroupe', ['$scope', '$rootScope', 'troupeRepository', function($scope, $rootScope, troupeRepository) {
	$scope.deleteTroupe = function(id, csrfHeader, csrfToken) {
		troupeRepository.deleteTroupe(id, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {$rootScope.$broadcast('troupesUpdated')}).
			error(function(data, status, headers, config) {console.log("deleteTroupe failed")});
	};
}]).
controller('addTroupe', ['$scope', '$rootScope', 'troupeRepository', 'settingsSource', function($scope, $rootScope, troupeRepository, settingsSource) {
	$scope.settings = [];
	for(var i = 0; i< settingsSource.get().length; i++) {
		$scope.settings[i] = {label: settingsSource.get()[i], value: i};
	}
 	$scope.setting = $scope.settings[0];
	$scope.submit = function(csrfHeader, csrfToken) {
		troupeRepository.addTroupe({'name': $scope.name, 'setting': $scope.setting.value}, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {$rootScope.$broadcast('troupesUpdated')}).
			error(function(data, status, headers, config) {console.log("addTroupe failed")});
	};
}]);

angular.module('admin.troupe.directives', ['admin.troupe.services']).
directive('listTroupes', ['troupeRepository', function(troupeRepository) {
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		link: function(scope, iElement, iAttrs) {
			scope.troupes = troupeRepository.getTroupes();
			scope.$on('troupesUpdated', function(event) {
				scope.troupes = troupeRepository.getTroupes();  
			});
		},
		templateUrl: '/js/admin/troupe/display.html'
	};
}]).
directive('addTroupe', [function() {
	return {
		restrict: 'E',
		scope: {
			csrf: '='
		},
		templateUrl: '/js/admin/troupe/add.html'
	};
}]);

angular.module('admin.troupe.filters', ['filters.setting']);

angular.module('admin.troupe', ['admin.troupe.filters', 'admin.troupe.controllers', 'admin.troupe.directives', 'admin.troupe.services']);