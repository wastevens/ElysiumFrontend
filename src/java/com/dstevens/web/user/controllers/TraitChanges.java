package com.dstevens.web.user.controllers;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;
import com.dstevens.characters.traits.powers.disciplines.Discipline;

public enum TraitChanges {

	CLAN {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.clan(Clan.values()[value]);
		}
	},
	BLOODLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.bloodline(Bloodline.values()[value]);
		}
	},
	IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.inClanPower(Discipline.values()[value]);
		}
	},
	REMOVE_IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.inClanPower(Discipline.values()[value]).remove();
		}
	};
	
	public abstract TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value);
	
}
