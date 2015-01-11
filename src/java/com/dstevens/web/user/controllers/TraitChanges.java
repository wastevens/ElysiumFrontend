package com.dstevens.web.user.controllers;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;
import com.dstevens.characters.traits.powers.disciplines.Discipline;

public enum TraitChanges {

	// 0
	CLAN {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.clan(Clan.values()[value]);
		}
	},
	// 1
	BLOODLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.bloodline(Bloodline.values()[value]);
		}
	},
	// 2
	IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.inClanPower(Discipline.values()[value]);
		}
	},
	// 3
	REMOVE_IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.inClanPower(Discipline.values()[value]).remove();
		}
	},
	// 4
	SET_PHYSICAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.attribute(Attribute.PHYSICAL, value);
		}
	},
	// 5
	SET_SOCIAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.attribute(Attribute.SOCIAL, value);
		}
	},
	// 6
	SET_MENTAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.attribute(Attribute.MENTAL, value);
		}
	},
	// 7
	WITH_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.focus(PhysicalAttributeFocus.values()[value]);
		}
	},
	// 8
	WITH_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.focus(SocialAttributeFocus.values()[value]);
		}
	},
	// 9
	WITH_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.focus(MentalAttributeFocus.values()[value]);
		}
	},
	// 10
	WITHOUT_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.focus(PhysicalAttributeFocus.values()[value]).remove();
		}
	},
	// 11
	WITHOUT_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.focus(SocialAttributeFocus.values()[value]).remove();
		}
	},
	// 12
	WITHOUT_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value) {
			return traitChangeFactory.focus(MentalAttributeFocus.values()[value]).remove();
		}
	},
	;
	
	public abstract TraitChange<?> using(TraitChangeFactory traitChangeFactory, int value);
	
}
