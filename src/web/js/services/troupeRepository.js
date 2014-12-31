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
		addTroupe: function(troupeToPost, csrfHeader, csrfToken) {
			return csrfResource.post(this.url, troupeToPost, csrfHeader, csrfToken);
		},
		updateTroupe: function(troupe, csrfHeader, csrfToken) {
			return csrfResource.put(this.url + '/' + troupe.id, troupe, csrfHeader, csrfToken);
		},
		deleteTroupe: function(id, csrfHeader, csrfToken) {
			return csrfResource.delete(this.url + '/' + id, csrfHeader, csrfToken);
		}
	};
}]);