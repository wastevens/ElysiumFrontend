package com.dstevens.web.trait.displayable.vampire;

import com.dstevens.characters.traits.powers.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.web.trait.DisplayableTraitSource;

public enum DisplayableThaumaturgicalRitual implements DisplayableTraitSource {

	BLOOD_MASTERY                      ("Blood Mastery",                      ThaumaturgicalRitual.BLOOD_MASTERY.rating()),
    BIND_THE_ACCUSING_TONGUE           ("Bind the Accusing Tongue",           ThaumaturgicalRitual.BIND_THE_ACCUSING_TONGUE.rating()),
    COMMUNICATE_WITH_KINDRED_SIRE      ("Communicate with Kindred Sire",      ThaumaturgicalRitual.COMMUNICATE_WITH_KINDRED_SIRE.rating()),
    DEFENSE_OF_THE_SACRED_HAVEN        ("Defense of the Sacred Haven",        ThaumaturgicalRitual.DEFENSE_OF_THE_SACRED_HAVEN.rating()),
    ENGAGING_THE_VESSEL_OF_TRANSFERENCE("Engaging the Vessel of Transference",ThaumaturgicalRitual.ENGAGING_THE_VESSEL_OF_TRANSFERENCE.rating()),
    ILLUMINATE_THE_TRAIL_OF_PREY       ("Illuminate the Trail of Prey",       ThaumaturgicalRitual.ILLUMINATE_THE_TRAIL_OF_PREY.rating()),
    PRINCIPAL_FOCUS_OF_VITAE_INFUSION  ("Principal Focus of Vitae Infusion",  ThaumaturgicalRitual.PRINCIPAL_FOCUS_OF_VITAE_INFUSION.rating()),
    WARDING_CIRCLE                     ("Warding Circle",                     ThaumaturgicalRitual.WARDING_CIRCLE.rating()),
    BANISH_BIG_BROTHER                 ("Banish Big Brother",                 ThaumaturgicalRitual.BANISH_BIG_BROTHER.rating()),
    BURNING_BLADE                      ("Burning Blade",                      ThaumaturgicalRitual.BURNING_BLADE.rating()),
    CRAFT_BLOODSTONE                   ("Craft Bloodstone",                   ThaumaturgicalRitual.CRAFT_BLOODSTONE.rating()),
    EYES_OF_THE_NIGHTHAWK              ("Eyes of the Nighthawk",              ThaumaturgicalRitual.EYES_OF_THE_NIGHTHAWK.rating()),
    ILLUSION_OF_PEACEFUL_DEATH         ("Illusion of Peaceful Death",         ThaumaturgicalRitual.ILLUSION_OF_PEACEFUL_DEATH.rating()),
    MACHINE_BLITZ                      ("Machine Blitz",                      ThaumaturgicalRitual.MACHINE_BLITZ.rating()),
    RECURE_OF_THE_HOMELAND             ("Recure of the Homeland",             ThaumaturgicalRitual.RECURE_OF_THE_HOMELAND.rating()),
    DETECT_THE_HIDDEN_OBSERVER         ("Detect the Hidden Observer",         ThaumaturgicalRitual.DETECT_THE_HIDDEN_OBSERVER.rating()),
    FLESH_OF_FIERY_TOUCH               ("Flesh of Fiery Touch",               ThaumaturgicalRitual.FLESH_OF_FIERY_TOUCH.rating()),
    INCORPOREAL_PASSAGE                ("Incorporeal Passage",                ThaumaturgicalRitual.INCORPOREAL_PASSAGE.rating()),
    MIRROR_OF_SECOND_SIGHT             ("Mirror of Second Sight",             ThaumaturgicalRitual.MIRROR_OF_SECOND_SIGHT.rating()),
    PAVIS_OF_FOUL_PRESENCE             ("Pavis of Foul Presence",             ThaumaturgicalRitual.PAVIS_OF_FOUL_PRESENCE.rating()),
    SOUL_OF_THE_HOMUNCULI              ("Soul of the Homunculi",              ThaumaturgicalRitual.SOUL_OF_THE_HOMUNCULI.rating()),
    SHAFT_OF_BELATED_QUIESCENCE        ("Shaft of Belated Quiescence",        ThaumaturgicalRitual.SHAFT_OF_BELATED_QUIESCENCE.rating()),
    INNOCENCE_OF_THE_CHILDS_HEART      ("Innocence of the Child's Heart",     ThaumaturgicalRitual.INNOCENCE_OF_THE_CHILDS_HEART.rating()),
    MIRROR_WALK                        ("Mirror Walk",                        ThaumaturgicalRitual.MIRROR_WALK.rating()),
    SEVERED_HAND                       ("Severed Hand",                       ThaumaturgicalRitual.SEVERED_HAND.rating()),
    SCRY                               ("Scry",                               ThaumaturgicalRitual.SCRY.rating()),
    BLOOD_CONTRACT                     ("Blood Contract",                     ThaumaturgicalRitual.BLOOD_CONTRACT.rating()),
    COBRAS_FAVOR                       ("Cobra's Favor",                      ThaumaturgicalRitual.COBRAS_FAVOR.rating()),
    PAPER_FLESH                        ("Paper Flesh",                        ThaumaturgicalRitual.PAPER_FLESH.rating()),
    STONE_OF_THE_TRUE_FORM             ("Stone of the True Form",             ThaumaturgicalRitual.STONE_OF_THE_TRUE_FORM.rating())
    ;
	
	private final String display;
	private final int rating;

    private DisplayableThaumaturgicalRitual(String display, int rating) {
        this.display = display;
		this.rating = rating;
    }
    
    @Override
    public String display() {
    	return display;
    }
    
    @Override
    public int rating() {
    	return rating;
    }
}
