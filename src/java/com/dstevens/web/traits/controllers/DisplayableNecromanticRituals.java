package com.dstevens.web.traits.controllers;

public enum DisplayableNecromanticRituals implements DisplayableTraitSource {

	CALL_OF_THE_HUNGRY_DEAD  ("Call of the Hungry Dead", 1),
    CIRCLE_OF_CERBERUS       ("Circle of Cerberus", 1),
    DARK_ASSISTANT           ("Dark Assistant", 1),
    EYES_OF_THE_GRAVE        ("Eyes of the Grave", 1),
    SMOKING_MIRROR           ("Smoking Mirror", 1),
    WARPING_THE_MORBID_VISAGE("Warping the Morbid Visage", 1),
    BLACK_BLOOD              ("Black Blood", 2),
    DIN_OF_THE_DAMNED        ("Din of the Damned", 2),
    SEPULCHRAL_BEACON        ("Sepulchral Beacon", 2),
    STAINED_SIGHT            ("Stained Sight", 2),
    SCALES_OF_MAAT           ("Scales of Maat", 2),
    MOLDERING_PRESENCE       ("Moldering Presence", 3),
    RISE_CERBERUS            ("Rise, Cerberus", 3),
    SPIRIT_BEACON            ("Spirit Beacon", 3),
    THE_SERVANT_UNDYING      ("The Servant Undying", 3),
    BASTONE_DIABOLICO        ("Bastone Diabolico", 4),
    LETHES_WATER             ("Lethe's Water", 4),
    RITUAL_OF_XIPE_TOTEC     ("Ritual of Xipe Totec", 4),
    STRENGTH_OF_ROTTEN_FLESH ("Strength of Rotten Flesh", 4),
    CHILL_OF_OBLIVION        ("Chill of Oblivion", 5),
    WEIGHT_OF_THE_TOMB       ("Weight of the Tomb", 5)
    ;
	
	private final String name;
	private final int rating;

    private DisplayableNecromanticRituals(String name, int rating) {
        this.name = name;
		this.rating = rating;
    }

	@Override
	public DisplayableTrait toDisplayableTrait() {
		return new DisplayableTrait(name, ordinal(), rating);
	}
}
