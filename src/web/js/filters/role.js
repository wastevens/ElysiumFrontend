angular.module('filters.role', ['sources.roles']).
filter('role', ['rolesSource', function(rolesSource) {
	return function(input) {
		return rolesSource.get()[input];
	};
}]).
filter('roles', ['$filter', function($filter) {
	return function(input) {
		var roleNames = [];
		for(var i = 0;i<input.length;i++) {
			roleNames[i] = $filter('role')(input[i]);
		}
		return roleNames;
	};
}]);