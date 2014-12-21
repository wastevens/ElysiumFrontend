angular.module('filters.role', ['sources.roles']).
filter('role', ['rolesSource', function(rolesSource) {
	return function(input) {
		var roleNames = [];
		for(var i = 0;i<input.length;i++) {
			roleNames[i] = rolesSource.get()[input[i]];
		}
		return roleNames;
	};
}]);