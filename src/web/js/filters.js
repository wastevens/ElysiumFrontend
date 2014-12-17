angular.module('filters.setting', []).
	filter('setting', function() {
	  return function(input) {
		switch(input) {
			case 0: return 'Camarilla';
			case 1: return 'Anarch';
			case 2: return 'Sabbat';
		}
		return 'No setting found for ' + input;
	  };
	}
);