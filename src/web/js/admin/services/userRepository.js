angular.module('admin.services.users', ['ngResource', 'services.csrfResource']).
factory('userRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/admin/users',
		getUsers: function() {
			return $resource(this.url, {}).query();
		},
		getUsersWithRole: function(role) {
			var users = this.getUsers();
			return users.filter(function(user) {
				return (user.roles.indexOf(role) > -1);
			});
		},
		getUserWithId: function(id) {
			return csrfResource.get(this.url + '/' + id);
		},
		updateUser: function(userToUpdate, csrfHeader, csrfToken) {
			return csrfResource.put(this.url + '/' + userToUpdate.id, userToUpdate, csrfHeader, csrfToken);
		}
	};
}]);