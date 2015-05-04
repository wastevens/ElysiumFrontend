angular.module('services.payments', ['ngResource']).
factory('paymentTypesRepository', ['$resource', function($resource) {
	return {
		url: '/values/payment-types',
		getPaymentTypes: function() {
			var resource = $resource(this.url);
			return resource.query().$promise;
		}
	};
}]);