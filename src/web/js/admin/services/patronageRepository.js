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
		},
		createPatronage: function(patronage) {
			var action = { 'post': { method:'POST' } };
			return $resource(this.url , null, action).post(patronage).$promise;
		},
		updatePatronage: function(patronage) {
			var action = { 'put': { method:'PUT' } };
			return $resource(this.url + '/:id' , null, action).put({id: patronage.membershipId}, patronage).$promise;
		}
	};
}]);