angular.module('admin.troupe.services', ['services.redirection', 'services.troupes', 'admin.services.users'])

angular.module('admin.troupe.controllers', ['admin.troupe.services', 'sources.vampire']).
controller('updateTroupe', ['$scope', '$rootScope', 'redirect', 'troupeRepository', 'userRepository', 'settingSource', function($scope, $rootScope, redirect, troupeRepository, userRepository, settingSource) {
	$scope.settings = [];
	for(var i = 0; i< settingSource.get().length; i++) {
		$scope.settings[i] = {label: settingSource.get()[i], value: i};
	}
	$scope.setting = $scope.settings[$scope.troupe.setting];
	
	$scope.all_storytellers = userRepository.getUsersWithRole(1);
		
	$scope.storytellers = $scope.troupe.storytellers;
	
	$scope.submit = function() {
		$scope.troupe.storytellers = $scope.storytellers;
		troupeRepository.updateTroupe($scope.troupe).
			success(function(data, status, headers, config) {redirect.toUrl('/admin/page/troupes')}).
			error(function(data, status, headers, config) {console.log("addTroupe failed")});
		console.log('Update troupe ' + $scope.troupe);
	};
}]).

controller('deleteTroupe', ['$scope', '$rootScope', 'troupeRepository', function($scope, $rootScope, troupeRepository) {
	$scope.deleteTroupe = function(id) {
		troupeRepository.deleteTroupe(id).
			success(function(data, status, headers, config) {$rootScope.$broadcast('troupesUpdated')}).
			error(function(data, status, headers, config) {console.log("deleteTroupe failed")});
	};
}]).
controller('addTroupe', ['$scope', '$rootScope', 'troupeRepository', 'settingSource', function($scope, $rootScope, troupeRepository, settingSource) {
	$scope.settings = [];
	for(var i = 0; i< settingSource.get().length; i++) {
		$scope.settings[i] = {label: settingSource.get()[i], value: i};
	}
 	$scope.setting = $scope.settings[0];
	$scope.submit = function() {
		troupeRepository.addTroupe({'name': $scope.name, 'setting': $scope.setting.value}).
			success(function(data, status, headers, config) {$rootScope.$broadcast('troupesUpdated')}).
			error(function(data, status, headers, config) {console.log("addTroupe failed")});
	};
}]);

angular.module('admin.troupe.directives', ['admin.troupe.services']).
directive('manageTroupe', ['troupeRepository', function(troupeRepository) {
	return {
		restrict: 'E',
		scope: {
			troupe: '='
		},
		templateUrl: '/js/admin/troupe/manage.html'
	};
}]).
directive('listTroupes', ['troupeRepository', function(troupeRepository) {
	return {
		restrict: 'E',
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
		templateUrl: '/js/admin/troupe/add.html'
	};
}]);

angular.module('admin.troupe.filters', ['filters.vampire']);

angular.module('admin.troupe', ['admin.troupe.filters', 'admin.troupe.controllers', 'admin.troupe.directives', 'admin.troupe.services']);