package com.dstevens.web.user.controllers;

import java.util.Optional;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.skills.Skill;

import static com.dstevens.collections.Sets.set;

public enum TraitChanges {

	// 0
	CLAN {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.clan(Clan.values()[trait.value]);
		}
	},
	// 1
	BLOODLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.bloodline(Bloodline.values()[trait.value]);
		}
	},
	// 2
	IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.inClanPower(Discipline.values()[trait.value]);
		}
	},
	// 3
	REMOVE_IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.inClanPower(Discipline.values()[trait.value]).remove();
		}
	},
	// 4
	SET_PHYSICAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.attribute(Attribute.PHYSICAL, trait.value);
		}
	},
	// 5
	SET_SOCIAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.attribute(Attribute.SOCIAL, trait.value);
		}
	},
	// 6
	SET_MENTAL_ATTRIBUTE {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.attribute(Attribute.MENTAL, trait.value);
		}
	},
	// 7
	WITH_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.focus(PhysicalAttributeFocus.values()[trait.value]);
		}
	},
	// 8
	WITH_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.focus(SocialAttributeFocus.values()[trait.value]);
		}
	},
	// 9
	WITH_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.focus(MentalAttributeFocus.values()[trait.value]);
		}
	},
	// 10
	WITHOUT_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.focus(PhysicalAttributeFocus.values()[trait.value]).remove();
		}
	},
	// 11
	WITHOUT_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.focus(SocialAttributeFocus.values()[trait.value]).remove();
		}
	},
	// 12
	WITHOUT_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.focus(MentalAttributeFocus.values()[trait.value]).remove();
		}
	},
	// 13
	WITH_SKILL {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.skill(Skill.values()[trait.value], Optional.ofNullable(trait.rating).orElse(0), trait.specialization, set());
		}
	},
	// 14
	REMOVE_SKILL {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int value = trait.value;
			return traitChangeFactory.skill(Skill.values()[value], Optional.ofNullable(trait.rating).orElse(0), trait.specialization, set()).remove();
		}
	},
	// 13
	WITH_BACKGROUND {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.background(Background.values()[trait.value], Optional.ofNullable(trait.rating).orElse(0), trait.specialization, set());
		}
	},
	// 14
	REMOVE_BACKGROUND {
		@Override
		public TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int value = trait.value;
			return traitChangeFactory.background(Background.values()[value], Optional.ofNullable(trait.rating).orElse(0), trait.specialization, set()).remove();
		}
	},
	;
	
	public abstract TraitChange<?> using(TraitChangeFactory traitChangeFactory, RawTraitChange trait);
	
}
