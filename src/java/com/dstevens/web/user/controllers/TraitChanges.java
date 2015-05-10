package com.dstevens.web.user.controllers;

import java.util.Optional;

import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.trait.TraitQualities;
import com.dstevens.character.trait.TraitQualitiesBuilder;
import com.dstevens.character.trait.TraitType;
import com.dstevens.character.trait.attribute.Attribute;
import com.dstevens.character.trait.attribute.focus.MentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.PhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.SocialAttributeFocus;
import com.dstevens.character.trait.background.Background;
import com.dstevens.character.trait.change.TraitChange;
import com.dstevens.character.trait.change.TraitChangeFactory;
import com.dstevens.character.trait.power.discipline.Discipline;
import com.dstevens.character.trait.power.discipline.Technique;
import com.dstevens.character.trait.skill.Skill;
import com.dstevens.utilities.Identified;

import static com.dstevens.collections.Sets.set;

public enum TraitChanges implements Identified<Integer> {

	CLAN(0) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.CLAN, Clan.values()[trait.trait], TraitQualities.NONE);
		}
	},
	BLOODLINE(1) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.BLOODLINE, Bloodline.values()[trait.trait], TraitQualities.NONE);
		}
	},
	IN_CLAN_DISCIPLINE(2) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.trait], TraitQualities.NONE);
		}
	},
	REMOVE_IN_CLAN_DISCIPLINE(3) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.trait], TraitQualities.NONE).remove();
		}
	},
	SET_PHYSICAL_ATTRIBUTE(4) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.ATTRIBUTE, Attribute.PHYSICAL, rated(trait.trait));
		}
	},
	SET_SOCIAL_ATTRIBUTE(5) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.ATTRIBUTE, Attribute.SOCIAL, rated(trait.trait));
		}
	},
	// 6
	SET_MENTAL_ATTRIBUTE(6) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.ATTRIBUTE, Attribute.MENTAL, rated(trait.trait));
		}
	},
	WITH_PHYSICAL_ATTRIBUTE_FOCUS(7) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.PHYSICAL_FOCUS, PhysicalAttributeFocus.values()[trait.trait], TraitQualities.NONE);
		}
	},
	WITH_SOCIAL_ATTRIBUTE_FOCUS(8) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.SOCIAL_FOCUS, SocialAttributeFocus.values()[trait.trait], TraitQualities.NONE);
		}
	},
	WITH_MENTAL_ATTRIBUTE_FOCUS(9) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.MENTAL_FOCUS, MentalAttributeFocus.values()[trait.trait], TraitQualities.NONE);
		}
	},
	WITHOUT_PHYSICAL_ATTRIBUTE_FOCUS(10) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.PHYSICAL_FOCUS, PhysicalAttributeFocus.values()[trait.trait], TraitQualities.NONE).remove();
		}
	},
	WITHOUT_SOCIAL_ATTRIBUTE_FOCUS(11) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.SOCIAL_FOCUS, SocialAttributeFocus.values()[trait.trait], TraitQualities.NONE).remove();
		}
	},
	WITHOUT_MENTAL_ATTRIBUTE_FOCUS(12) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.MENTAL_FOCUS, MentalAttributeFocus.values()[trait.trait], TraitQualities.NONE).remove();
		}
	},
	WITH_SKILL(13) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			if(rating > 0) {
				return traitChangeFactory.traitChange(TraitType.SKILL, Skill.values()[trait.trait], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build());
			}
			return traitChangeFactory.traitChange(TraitType.SKILL, Skill.values()[trait.trait], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	REMOVE_SKILL(14) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.SKILL, Skill.values()[trait.trait], new TraitQualitiesBuilder().rated(0).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	WITH_BACKGROUND(15) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			if(rating > 0) {
				return traitChangeFactory.traitChange(TraitType.BACKGROUND, Background.values()[trait.trait], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build());
			}
			return traitChangeFactory.traitChange(TraitType.BACKGROUND, Background.values()[trait.trait], new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	REMOVE_BACKGROUND(16) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.BACKGROUND, Background.values()[trait.trait], new TraitQualitiesBuilder().rated(0).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	WITH_DISCIPLINE(17) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			if(rating > 0) {
				return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.trait], rated(rating));
			}
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.trait], rated(rating)).remove();
		}
	},
	REMOVE_DISCIPLINE(18) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.DISCIPLINE, Discipline.values()[trait.trait], rated(0)).remove();
		}
	},
	WITH_TECHNIQUE(19) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.TECHNIQUE, Technique.values()[trait.trait], TraitQualities.NONE);
		}
	},
	REMOVE_TECHNIQUE(20) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			return traitChangeFactory.traitChange(TraitType.TECHNIQUE, Technique.values()[trait.trait], TraitQualities.NONE).remove();
		}
	},
	WITH_TRAIT_CHANGE(21) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			TraitType traitType = TraitType.values()[0];
			return traitChangeFactory.traitChange(traitType, traitType.traits[trait.trait], 
					                              new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build());
		}
	},
	WITHOUT_TRAIT_CHANGE(22) {
		@Override
		public TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait) {
			int rating = Optional.ofNullable(trait.rating).orElse(0);
			TraitType traitType = TraitType.values()[0];
			return traitChangeFactory.traitChange(traitType, traitType.traits[trait.trait],
					                              new TraitQualitiesBuilder().rated(rating).specialized(trait.specialization).focused(set()).build()).remove();
		}
	},
	;

	private final int id;

	private TraitChanges(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public abstract TraitChange using(TraitChangeFactory traitChangeFactory, RawTraitChange trait);
	
	private static TraitQualities rated(int rating) {
		return new TraitQualitiesBuilder().rated(rating).build();
	}
	
}
