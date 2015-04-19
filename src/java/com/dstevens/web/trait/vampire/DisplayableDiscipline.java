package com.dstevens.web.trait.vampire;

import com.dstevens.web.trait.DisplayableTraitSource;

public enum DisplayableDiscipline implements DisplayableTraitSource {
    ANIMALISM(    "Animalism",     "Discipline"),
    AUSPEX(       "Auspex",        "Discipline"),
    CELERITY(     "Celerity",      "Discipline"),
    CHIMERSTRY(   "Chimerstry",    "Discipline"),
    DAIMOINON(    "Daimoinon",     "Discipline"),
    DEMENTATION(  "Dementation",   "Discipline"),
    DOMINATE(     "Dominate",      "Discipline"),
    FORTITUDE(    "Fortitude",     "Discipline"),
    MELPOMINEE(   "Melpominee",    "Discipline"),
    MYTHERCERIA(  "Mytherceria",   "Discipline"),
    OBEAH(        "Obeah",         "Discipline"),
    OBFUSCATE(    "Obfuscate",     "Discipline"),
    OBTENEBRATION("Obtenebration", "Discipline"),
    POTENCE(      "Potence",       "Discipline"),
    PRESENCE(     "Presence",      "Discipline"),
    PROTEAN(      "Protean",       "Discipline"),
    QUIETUS(      "Quietus",       "Discipline"),
    SERPENTIS(    "Serpentis",     "Discipline"),
    TEMPORSIS(    "Temporsis",     "Discipline"),
    THANATOSIS(   "Thanatosis",    "Discipline"),
    VALEREN(      "Valeren",       "Discipline"),
    VICISSITUDE(  "Vicissitude",   "Discipline"),
    VISCERATIKA(  "Visceratika",   "Discipline"),
    
    SEPULCHRE_PATH("Sepulchre Path", "Necromancy"),
    BONE_PATH(     "Bone Path",      "Necromancy"),
    ASH_PATH(      "Ash Path",       "Necromancy"),
    MORTIS_PATH(   "Mortis Path",    "Necromancy"),
    
    PATH_OF_BLOOD(            "Path of Blood",             "Thaumaturgy"),
    PATH_OF_CONJURING(        "Path of Conjuring",         "Thaumaturgy"),
    PATH_OF_CORRUPTION(       "Path of Corruption",        "Thaumaturgy"),
    PATH_OF_ELEMENTAL_MASTERY("Path of Elemental Mastery", "Thaumaturgy"),
    LURE_OF_FLAMES(           "Lure of Flames",            "Thaumaturgy"),
    MOVEMENT_OF_THE_MIND(     "Movement of the Mind",      "Thaumaturgy"),
    PATH_OF_TECHNOMANCY(      "Path of Technomancy",       "Thaumaturgy"),
    PATH_OF_WEATHER_MASTERY(  "Path of Weather Mastery",   "Thaumaturgy");
    
    private final String display;
	private final String subcategory;

	private DisplayableDiscipline(String display, String subcategory) {
		this.display = display;
		this.subcategory = subcategory;
	}
    
	@Override
	public String display() {
		return display;
	}
	
	@Override
	public String subcategory() {
		return subcategory;
	}

}
