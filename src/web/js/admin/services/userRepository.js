angular.module('admin.services.users', ['ngResource', 'configuration.authorization']).
factory('userRepository', ['$resource', '$http', function($resource, $http) {
	return {
		url: '/admin/users',
		getUsers: function() {
			return $resource(this.url, {}).query();
		},
		getUsersWithRole: function(role) {
			return $resource(this.url + '/role/' + role, {}).query();
		},
		getUserWithId: function(id) {
			return $http.get(this.url + '/' + id);
		},
		updateUser: function(userToUpdate) {
			return $http.put(this.url + '/' + userToUpdate.id, userToUpdate);
		}
	};
}]);