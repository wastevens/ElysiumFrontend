package com.dstevens.web.trait.vampire;

import com.dstevens.web.trait.DisplayableTraitSource;

public enum DisplayableTechnique implements DisplayableTraitSource {
	AN_DA_SHEALLADH           ("An Da Shealladh"),
    ANIMAL_SUCCULENCE         ("Animal Succulence"),
    ANIMAL_SWARM              ("Animal Swarm"),
    ARMOR_OF_DARKNESS         ("Armor of Darkness"),
    BANSHEES_WAIL             ("Banshee's Wail"),
    BLIND_THE_SUN             ("Blind the Sun"),
    BULLS_EYE                 ("Bull's Eye"),
    CONTROL_THE_SAVAGE_BEAST  ("Control the Savage Beast"),
    DEATHS_GRIP               ("Death's Grip"),
    DENIAL_OF_APHRODITES_FAVOR("Denial of Aphrodite's Favor"),
    DEVIOUS_FEINT             ("Devious Feint"),
    ECHO_PSYCHOSIS            ("Echo Psychosis"),
    FEARFUL_BLOW              ("Fearful Blow"),
    FEAST_OF_SHADOWS          ("Feast of Shadows"),
    FENRIRS_BOON              ("Fenrir's Boon"),
    FIST_OF_STONE             ("Fist of Stone"),
    FLAMES_BANE               ("Flames Bane"),
    GUARDIAN_LION             ("Guardian Lion"),
    INSTINCTIVE_COMMAND       ("Instinctive Command"),
    LIGEIAS_LAMENT            ("Ligeia's Lament"),
    MERCURIAL_VITALITY        ("Mercurial Vitality"),
    MONOLOGUE                 ("Monologue"),
    MISPLACED_AFFECTION       ("Misplaced Affection"),
    MISLEADING_WOUNDS         ("Misleading Wounds"),
    NIGHTINGALES_SONG         ("Nightingales Song"),
    QUICKENED_BLOOD           ("Quickened Blood"),
    RADIANT_GAZE              ("Radiant Gaze"),
    REFLECTION_OF_ENDURANCE   ("Reflection of Endurance"),
    REFLECTION_OF_MERCY       ("Reflection of Mercy"),
    RELENTLESS_PURSUIT        ("Relentless Pursuit"),
    RETAIN_THE_QUICK_BLOOD    ("Retain the Quick Blood"),
    SECOND_WIND               ("Second Wind"),
    SYMPATHETIC_AGONY         ("Sympathetic Agony"),
    TELEPATHIC_DIRECTIVE      ("Telepathic Directive"),
    UNNATURAL_GRACE           ("Unnatural Grace"),
    VISIONS_OF_THE_TRUE_FORM  ("Visions of the True Form"),
    WILL_TO_SURVIVE           ("Will to Survive"),
    WOLFS_BLOOD               ("Wolf's Blood"),
    ;

	private final String display;

	private DisplayableTechnique(String display) {
		this.display = display;
	}
	
	@Override
	public String display() {
		return display;
	}
}
