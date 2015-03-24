angular.module('services.troupes', ['ngResource', 'services.csrfResource']).
factory('troupeRepository', ['$resource', 'csrfResource', function($resource, csrfResource) {
	return {
		url: '/troupes',
		getTroupes: function() {
			return $resource(this.url, {}).query();
		},
		getTroupe: function(id) {
			return $resource(this.url + '/' + id, {}).get();
		},
		addTroupe: function(troupeToPost) {
			return csrfResource.post(this.url, troupeToPost);
		},
		updateTroupe: function(troupe) {
			return csrfResource.post(this.url + '/' + troupe.id, troupe);
		},
		deleteTroupe: function(id) {
			return csrfResource.delete(this.url + '/' + id);
		}
	};
}]);