package com.dstevens.web.traits.controllers;

import com.dstevens.characters.traits.powers.magic.necromancy.NecromanticRitual;

public enum DisplayableNecromanticRitual implements DisplayableTraitSource {

	CALL_OF_THE_HUNGRY_DEAD  ("Call of the Hungry Dead",   NecromanticRitual.CALL_OF_THE_HUNGRY_DEAD.rating()),
    CIRCLE_OF_CERBERUS       ("Circle of Cerberus",        NecromanticRitual.CIRCLE_OF_CERBERUS.rating()),
    DARK_ASSISTANT           ("Dark Assistant",            NecromanticRitual.DARK_ASSISTANT.rating()),
    EYES_OF_THE_GRAVE        ("Eyes of the Grave",         NecromanticRitual.EYES_OF_THE_GRAVE.rating()),
    SMOKING_MIRROR           ("Smoking Mirror",            NecromanticRitual.SMOKING_MIRROR.rating()),
    WARPING_THE_MORBID_VISAGE("Warping the Morbid Visage", NecromanticRitual.WARPING_THE_MORBID_VISAGE.rating()),
    BLACK_BLOOD              ("Black Blood",               NecromanticRitual.BLACK_BLOOD.rating()),
    DIN_OF_THE_DAMNED        ("Din of the Damned",         NecromanticRitual.DIN_OF_THE_DAMNED.rating()),
    SEPULCHRAL_BEACON        ("Sepulchral Beacon",         NecromanticRitual.SEPULCHRAL_BEACON.rating()),
    STAINED_SIGHT            ("Stained Sight",             NecromanticRitual.STAINED_SIGHT.rating()),
    SCALES_OF_MAAT           ("Scales of Maat",            NecromanticRitual.SCALES_OF_MAAT.rating()),
    MOLDERING_PRESENCE       ("Moldering Presence",        NecromanticRitual.MOLDERING_PRESENCE.rating()),
    RISE_CERBERUS            ("Rise, Cerberus",            NecromanticRitual.RISE_CERBERUS.rating()),
    SPIRIT_BEACON            ("Spirit Beacon",             NecromanticRitual.SPIRIT_BEACON.rating()),
    THE_SERVANT_UNDYING      ("The Servant Undying",       NecromanticRitual.THE_SERVANT_UNDYING.rating()),
    BASTONE_DIABOLICO        ("Bastone Diabolico",         NecromanticRitual.BASTONE_DIABOLICO.rating()),
    LETHES_WATER             ("Lethe's Water",             NecromanticRitual.LETHES_WATER.rating()),
    RITUAL_OF_XIPE_TOTEC     ("Ritual of Xipe Totec",      NecromanticRitual.RITUAL_OF_XIPE_TOTEC.rating()),
    STRENGTH_OF_ROTTEN_FLESH ("Strength of Rotten Flesh",  NecromanticRitual.STRENGTH_OF_ROTTEN_FLESH.rating()),
    CHILL_OF_OBLIVION        ("Chill of Oblivion",         NecromanticRitual.CHILL_OF_OBLIVION.rating()),
    WEIGHT_OF_THE_TOMB       ("Weight of the Tomb",        NecromanticRitual.WEIGHT_OF_THE_TOMB.rating())
    ;
	
	private final String display;
	private final int rating;

    private DisplayableNecromanticRitual(String display, int rating) {
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
