angular.module('admin.services.patronage', ['ngResource', 'configuration.authorization']).
factory('patronageRepository', ['$resource', '$http', function($resource, $http) {
	return {
		url: '/admin/patronages',
		getPatronages: function() {
			return $resource(this.url, {}).query().$promise;
		},
		getUnassignedPatronages: function() {
			return $resource(this.url + '/unassigned', {}).query().$promise;
		},
		getPatronage: function(id) {
			return $resource(this.url + '/' + id).get().$promise;
		}
	};
}]);