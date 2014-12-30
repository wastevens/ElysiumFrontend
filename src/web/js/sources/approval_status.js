angular.module('sources.status.approval', []).
factory('approvalStatusSource', [function() {
	var map = [];
	
	map[0] = "In Creation";
	map[1] = "Approval Requested";
	map[2] = "Approved";
	
	return {
		get: function() {
			return map;
		}
	}
}]);