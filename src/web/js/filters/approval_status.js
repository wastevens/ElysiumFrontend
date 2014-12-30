angular.module('filters.status.approval', ['sources.status.approval']).
filter('approval_status', ['approvalStatusSource', function(approvalStatusSource) {
	return function(input) {
		return approvalStatusSource.get()[input];
	};
}]);