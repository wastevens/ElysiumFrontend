angular.module('admin.services.users', ['ngResource', 'services.csrfResource']).
factory('userRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/admin/users',
		getUsers: function() {
			return $resource(this.url, {}).query();
		},
		getUsersWithRole: function(role) {
			return $resource(this.url + '/role/' + role, {}).query();
		},
		getUserWithId: function(id) {
			return csrfResource.get(this.url + '/' + id);
		},
		updateUser: function(userToUpdate) {
			return csrfResource.put(this.url + '/' + userToUpdate.id, userToUpdate);
		}
	};
}]);