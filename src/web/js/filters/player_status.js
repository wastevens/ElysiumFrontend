angular.module('filters.status.player', ['sources.status.player']).
filter('player_status', ['playerStatusSource', function(playerStatusSource) {
	return function(input) {
		return playerStatusSource.get()[input];
	};
}]);