angular.module('services.troupes', ['ngResource', 'configuration.authorization']).
factory('troupeRepository', ['$resource', '$http', function($resource, $http) {
	return {
		url: '/troupes',
		getTroupes: function() {
			return $resource(this.url, {}).query();
		},
		getTroupe: function(id) {
			return $resource(this.url + '/' + id, {}).get();
		},
		addTroupe: function(troupeToPost) {
			return $http.post(this.url, troupeToPost);
		},
		updateTroupe: function(troupe) {
			return $http.post(this.url + '/' + troupe.id, troupe);
		},
		deleteTroupe: function(id) {
			return $http.delete(this.url + '/' + id);
		}
	};
}]);