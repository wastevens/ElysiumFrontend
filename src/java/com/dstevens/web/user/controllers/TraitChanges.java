package com.dstevens.web.user.controllers;

import java.util.Optional;

import com.dstevens.characters.clans.Bloodline;
import com.dstevens.characters.clans.Clan;
import com.dstevens.characters.traits.TraitQualities;
import com.dstevens.characters.traits.TraitQualitiesBuilder;
import com.dstevens.characters.traits.TraitType;
import com.dstevens.characters.traits.attributes.Attribute;
import com.dstevens.characters.traits.attributes.focuses.MentalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.PhysicalAttributeFocus;
import com.dstevens.characters.traits.attributes.focuses.SocialAttributeFocus;
import com.dstevens.characters.traits.backgrounds.Background;
import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;
import com.dstevens.characters.traits.powers.disciplines.Discipline;
import com.dstevens.characters.traits.powers.disciplines.Technique;
import com.dstevens.characters.traits.skills.Skill;

import static com.dstevens.collections.Sets.set;

public enum TraitChanges {

	// 0
	CLAN {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.CLAN, Clan.values()[trait.value], TraitQualities.NONE);
		}
	},
	// 1
	BLOODLINE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.BLOODLINE, Bloodline.values()[trait.value], TraitQualities.NONE);
		}
	},
	// 2
	IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.value], TraitQualities.NONE);
		}
	},
	// 3
	REMOVE_IN_CLAN_DISCIPLINE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.value], TraitQualities.NONE).remove();
		}
	},
	// 4
	SET_PHYSICAL_ATTRIBUTE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.ATTRIBUTE, Attribute.PHYSICAL, rated(trait.value));
		}
	},
	// 5
	SET_SOCIAL_ATTRIBUTE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.ATTRIBUTE, Attribute.SOCIAL, rated(trait.value));
		}
	},
	// 6
	SET_MENTAL_ATTRIBUTE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.ATTRIBUTE, Attribute.MENTAL, rated(trait.value));
		}
	},
	// 7
	WITH_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.PHYSICAL_FOCUS, PhysicalAttributeFocus.values()[trait.value], TraitQualities.NONE);
		}
	},
	// 8
	WITH_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.SOCIAL_FOCUS, SocialAttributeFocus.values()[trait.value], TraitQualities.NONE);
		}
	},
	// 9
	WITH_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.MENTAL_FOCUS, MentalAttributeFocus.values()[trait.value], TraitQualities.NONE);
		}
	},
	// 10
	WITHOUT_PHYSICAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.PHYSICAL_FOCUS, PhysicalAttributeFocus.values()[trait.value], TraitQualities.NONE).remove();
		}
	},
	// 11
	WITHOUT_SOCIAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.SOCIAL_FOCUS, SocialAttributeFocus.values()[trait.value], TraitQualities.NONE).remove();
		}
	},
	// 12
	WITHOUT_MENTAL_ATTRIBUTE_FOCUS {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.MENTAL_FOCUS, MentalAttributeFocus.values()[trait.value], TraitQualities.NONE).remove();
		}
	},
	// 13
	WITH_SKILL {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			if(rating > 0) {
				return traitChangeFactory.traitChange(TraitType.SKILL, Skill.values()[trait.value], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build());
			}
			return traitChangeFactory.traitChange(TraitType.SKILL, Skill.values()[trait.value], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	// 14
	REMOVE_SKILL {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.SKILL, Skill.values()[trait.value], new TraitQualitiesBuilder().rated(0).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	// 15
	WITH_BACKGROUND {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			if(rating > 0) {
				return traitChangeFactory.traitChange(TraitType.BACKGROUND, Background.values()[trait.value], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build());
			}
			return traitChangeFactory.traitChange(TraitType.BACKGROUND, Background.values()[trait.value], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	// 16
	REMOVE_BACKGROUND {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.BACKGROUND, Background.values()[trait.value], new TraitQualitiesBuilder().rated(0).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	// 17
	WITH_DISCIPLINE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			if(rating > 0) {
				return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.value], rated(rating));
			}
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.value], rated(rating)).remove();
		}
	},
	// 18
	REMOVE_DISCIPLINE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.value], rated(0)).remove();
		}
	},
	// 19
	WITH_TECHNIQUE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.TECHNIQUE, Technique.values()[trait.value], TraitQualities.NONE);
		}
	},
	// 20
	REMOVE_TECHNIQUE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.TECHNIQUE, Technique.values()[trait.value], TraitQualities.NONE).remove();
		}
	},
	//21
	WITH_TRAIT_CHANGE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			return traitChangeFactory.traitChange(TraitType.values()[trait.traitType], Technique.values()[trait.value], 
					                              new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build());
		}
	},
	//22
	WITHOUT_TRAIT_CHANGE {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			return traitChangeFactory.traitChange(TraitType.values()[trait.traitType], Technique.values()[trait.value], 
					new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	;
	
	public abstract TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait);
	
	private static TraitQualities rated(int rating) {
		return new TraitQualitiesBuilder().rated(rating).build();
	}
	
}
