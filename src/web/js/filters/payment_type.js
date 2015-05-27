angular.module('filters.payment_type', ['sources.paymenttypes']).
filter('payment_type', [function() {
	return function(input) {
		return paymentTypes[input];
	};
}]);