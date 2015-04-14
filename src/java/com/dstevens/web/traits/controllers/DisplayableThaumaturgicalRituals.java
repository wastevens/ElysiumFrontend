package com.dstevens.web.traits.controllers;

public enum DisplayableThaumaturgicalRituals implements DisplayableTraitSource {

	BLOOD_MASTERY                      ("Blood Mastery",                      1),
    BIND_THE_ACCUSING_TONGUE           ("Bind the Accusing Tongue",           1),
    COMMUNICATE_WITH_KINDRED_SIRE      ("Communicate with Kindred Sire",      1),
    DEFENSE_OF_THE_SACRED_HAVEN        ("Defense of the Sacred Haven",        1),
    ENGAGING_THE_VESSEL_OF_TRANSFERENCE("Engaging the Vessel of Transference",1),
    ILLUMINATE_THE_TRAIL_OF_PREY       ("Illuminate the Trail of Prey",       1),
    PRINCIPAL_FOCUS_OF_VITAE_INFUSION  ("Principal Focus of Vitae Infusion",  1),
    WARDING_CIRCLE                     ("Warding Circle",                     1),
    BANISH_BIG_BROTHER                 ("Banish Big Brother",                 2),
    BURNING_BLADE                      ("Burning Blade",                      2),
    CRAFT_BLOODSTONE                   ("Craft Bloodstone",                   2),
    EYES_OF_THE_NIGHTHAWK              ("Eyes of the Nighthawk",              2),
    ILLUSION_OF_PEACEFUL_DEATH         ("Illusion of Peaceful Death",         2),
    MACHINE_BLITZ                      ("Machine Blitz",                      2),
    RECURE_OF_THE_HOMELAND             ("Recure of the Homeland",             2),
    DETECT_THE_HIDDEN_OBSERVER         ("Detect the Hidden Observer",         3),
    FLESH_OF_FIERY_TOUCH               ("Flesh of Fiery Touch",               3),
    INCORPOREAL_PASSAGE                ("Incorporeal Passage",                3),
    MIRROR_OF_SECOND_SIGHT             ("Mirror of Second Sight",             3),
    PAVIS_OF_FOUL_PRESENCE             ("Pavis of Foul Presence",             3),
    SOUL_OF_THE_HOMUNCULI              ("Soul of the Homunculi",              3),
    SHAFT_OF_BELATED_QUIESCENCE        ("Shaft of Belated Quiescence",        3),
    INNOCENCE_OF_THE_CHILDS_HEART      ("Innocence of the Child's Heart",     4),
    MIRROR_WALK                        ("Mirror Walk",                        4),
    SEVERED_HAND                       ("Severed Hand",                       4),
    SCRY                               ("Scry",                               4),
    BLOOD_CONTRACT                     ("Blood Contract",                     5),
    COBRAS_FAVOR                       ("Cobra's Favor",                      5),
    PAPER_FLESH                        ("Paper Flesh",                        5),
    STONE_OF_THE_TRUE_FORM             ("Stone of the True Form",             5)
    ;
	
	private final String name;
	private final int rating;

    private DisplayableThaumaturgicalRituals(String name, int rating) {
        this.name = name;
		this.rating = rating;
    }

	@Override
	public DisplayableTrait toDisplayableTrait() {
		return new DisplayableTrait(name, ordinal(), rating);
	}
}
