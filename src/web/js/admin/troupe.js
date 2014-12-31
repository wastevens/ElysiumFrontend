angular.module('admin.troupe.configuration', []).
config(function($locationProvider) { $locationProvider.html5Mode(true) });

angular.module('admin.troupe.services', ['admin.troupe.configuration', 'services.troupes', 'admin.services.users'])

angular.module('admin.troupe.controllers', ['admin.troupe.services', 'sources.settings']).
controller('updateTroupe', ['$scope', '$rootScope', '$location', '$window', 'troupeRepository', 'userRepository', 'settingsSource', function($scope, $rootScope, $location, $window, troupeRepository, userRepository, settingsSource) {
	$scope.settings = [];
	for(var i = 0; i< settingsSource.get().length; i++) {
		$scope.settings[i] = {label: settingsSource.get()[i], value: i};
	}
	$scope.setting = $scope.settings[$scope.troupe.setting];
	
	$scope.all_storytellers = userRepository.getUsersWithRole(1);
		
	$scope.storytellers = $scope.troupe.storytellers;
	
	$scope.submit = function(csrfHeader, csrfToken) {
		$scope.troupe.storytellers = $scope.storytellers;
		troupeRepository.updateTroupe($scope.troupe, csrfHeader, csrfToken).
			success(function(data, status, headers, config) {
				$location.path('/admin/page/troupes');
				$location.replace();
				$window.location.reload();
			}).
			error(function(data, status, headers, config) {console.log("addTroupe failed")});
		console.log('Update troupe ' + $scope.troupe);
	};
}]).

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
directive('manageTroupe', ['troupeRepository', function(troupeRepository) {
	return {
		restrict: 'E',
		scope: {
			csrf: '=',
			troupe: '='
		},
		templateUrl: '/js/admin/troupe/manage.html'
	};
}]).
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