package com.dstevens.web.user.controllers;

import java.util.Set;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.skills.Skill;

import static com.dstevens.collections.Sets.set;

public enum TraitChanges {

	// 0
	CLAN {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.clan(Clan.values()[ordinal]);
		}
	},
	// 1
	BLOODLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.bloodline(Bloodline.values()[ordinal]);
		}
	},
	// 2
	IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.inClanPower(Discipline.values()[ordinal]);
		}
	},
	// 3
	REMOVE_IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.inClanPower(Discipline.values()[ordinal]).remove();
		}
	},
	// 4
	SET_PHYSICAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.attribute(Attribute.PHYSICAL, ordinal);
		}
	},
	// 5
	SET_SOCIAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.attribute(Attribute.SOCIAL, ordinal);
		}
	},
	// 6
	SET_MENTAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.attribute(Attribute.MENTAL, ordinal);
		}
	},
	// 7
	WITH_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.focus(PhysicalAttributeFocus.values()[ordinal]);
		}
	},
	// 8
	WITH_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.focus(SocialAttributeFocus.values()[ordinal]);
		}
	},
	// 9
	WITH_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.focus(MentalAttributeFocus.values()[ordinal]);
		}
	},
	// 10
	WITHOUT_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.focus(PhysicalAttributeFocus.values()[ordinal]).remove();
		}
	},
	// 11
	WITHOUT_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.focus(SocialAttributeFocus.values()[ordinal]).remove();
		}
	},
	// 12
	WITHOUT_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
			return traitChangeFactory.focus(MentalAttributeFocus.values()[ordinal]).remove();
		}
	},
	// 13
	WITH_SKILL {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal, int rating, String specialization) {
			return traitChangeFactory.skill(Skill.values()[ordinal], rating, specialization, set());
		}
	},
	// 14
	REMOVE_SKILL {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal, int rating, String specialization) {
			return traitChangeFactory.skill(Skill.values()[ordinal], rating, specialization, set()).remove();
		}
	},
	;
	
	public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal) {
		return using(traitChangeFactory, ordinal, -1, null, null);
	}
	public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal, int rating) {
		return using(traitChangeFactory, ordinal, rating, null, null);
	}
	public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal, int rating, String specialization) {
		return using(traitChangeFactory, ordinal, rating, specialization, null);
	}
	public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal, int rating, Set<String> focuses) {
		return using(traitChangeFactory, ordinal, rating, null, focuses);
	}
	public TraitChange<?> using(TraitChangeFactory traitChangeFactory, int ordinal, int rating, String specialization, Set<String> focuses) {
		throw new IllegalStateException("Not implemented for " + this);
	}
	
}
