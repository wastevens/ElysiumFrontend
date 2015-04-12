angular.module('sources.paymenttypes', ['services.payments']).
factory('paymentTypesSource', ['paymentTypesRepository', function(paymentTypesRepository) {
	var map = [];
	paymentTypesRepository.getPaymentTypes().then(function(paymentTypes) {
		paymentTypes.forEach(function(paymentType, index) {
			map[index] = paymentType;
		});
	});
	return {
		get: function() {
			return map;
		}
	}	
}]);