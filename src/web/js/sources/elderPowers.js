angular.module('sources.elderPowers', ['sources.disciplines']).
factory('elderPowerSource', ['disciplineSource', function(disciplineSource) {
	var map = [];
	var i = -1;
	disciplineSource.get().then(function(disciplines) {
		map[++i] = {"name": "Crimson Fury", "ordinal": i, "discipline": disciplines[0]};
		map[++i] = {"name": "Intimidate the Beast", "ordinal": i, "discipline": disciplines[0]};
		map[++i] = {"name": "Clairvoyance", "ordinal": i, "discipline": disciplines[1]};
		map[++i] = {"name": "Psychic assault", "ordinal": i, "discipline": disciplines[1]};
		map[++i] = {"name": "Quickness", "ordinal": i, "discipline": disciplines[2]};
		map[++i] = {"name": "Projectile", "ordinal": i, "discipline": disciplines[2]};
		map[++i] = {"name": "Shared Nightmare", "ordinal": i, "discipline": disciplines[3]};
		map[++i] = {"name": "Army of Apparitions", "ordinal": i, "discipline": disciplines[3]};
		map[++i] = {"name": "Infernal Compact", "ordinal": i, "discipline": disciplines[4]};
		map[++i] = {"name": "Lingering Malaise", "ordinal": i, "discipline": disciplines[5]};
		map[++i] = {"name": "Deny", "ordinal": i, "discipline": disciplines[5]};
		map[++i] = {"name": "Mass Manipulation", "ordinal": i, "discipline": disciplines[6]};
		map[++i] = {"name": "Tyrant's Gaze", "ordinal": i, "discipline": disciplines[6]};
		map[++i] = {"name": "Personal Armor", "ordinal": i, "discipline": disciplines[7]};
		map[++i] = {"name": "Repair the Undead Flesh", "ordinal": i, "discipline": disciplines[7]};
		map[++i] = {"name": "Shattering Crescendo", "ordinal": i, "discipline": disciplines[8]};
		map[++i] = {"name": "Persistent Echo", "ordinal": i, "discipline": disciplines[8]};
		map[++i] = {"name": "Steal the Mind", "ordinal": i, "discipline": disciplines[9]};
		map[++i] = {"name": "Unburden the Beastial Soul", "ordinal": i, "discipline": disciplines[10]};
		map[++i] = {"name": "Cache", "ordinal": i, "discipline": disciplines[11]};
		map[++i] = {"name": "Phantom Hunter", "ordinal": i, "discipline": disciplines[11]};
		map[++i] = {"name": "Shadowstep", "ordinal": i, "discipline": disciplines[12]};
		map[++i] = {"name": "Shadow Twin", "ordinal": i, "discipline": disciplines[12]};
		map[++i] = {"name": "Force", "ordinal": i, "discipline": disciplines[13]};
		map[++i] = {"name": "Flick", "ordinal": i, "discipline": disciplines[13]};
		map[++i] = {"name": "Paralyzing Glance", "ordinal": i, "discipline": disciplines[14]};
		map[++i] = {"name": "Love", "ordinal": i, "discipline": disciplines[14]};
		map[++i] = {"name": "Earth Control", "ordinal": i, "discipline": disciplines[15]};
		map[++i] = {"name": "Shape Mastery", "ordinal": i, "discipline": disciplines[15]};
		map[++i] = {"name": "Blood Sweat", "ordinal": i, "discipline": disciplines[16]};
		map[++i] = {"name": "Baal's Bloody Talons", "ordinal": i, "discipline": disciplines[16]};
		map[++i] = {"name": "Seed of Corruption", "ordinal": i, "discipline": disciplines[17]};
		map[++i] = {"name": "Divine Image", "ordinal": i, "discipline": disciplines[17]};
		map[++i] = {"name": "Kiss of Lachesis", "ordinal": i, "discipline": disciplines[18]};
		map[++i] = {"name": "Corrupt the Flesh", "ordinal": i, "discipline": disciplines[19]};
		map[++i] = {"name": "Fiery Agony", "ordinal": i, "discipline": disciplines[20]};
		map[++i] = {"name": "Breath of the Dragon", "ordinal": i, "discipline": disciplines[21]};
		map[++i] = {"name": "Acid Blood", "ordinal": i, "discipline": disciplines[21]};
		map[++i] = {"name": "Bulwark", "ordinal": i, "discipline": disciplines[22]};
		map[++i] = {"name": "Furnace of Steel", "ordinal": i, "discipline": disciplines[22]};
	});
	return {
		get: function() {
			return map;
		}
	}	
}]);