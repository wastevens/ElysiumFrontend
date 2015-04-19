package com.dstevens.web.trait.vampire;

import com.dstevens.web.trait.DisplayableTrait;
import com.dstevens.web.trait.DisplayableTraitSource;

public enum DisplayableElderPower implements DisplayableTraitSource {

	CRIMSON_FURY              ("Crimson Fury", DisplayableDiscipline.ANIMALISM),
    INTIMIDATE_THE_BEAST      ("Intimidate the Beast", DisplayableDiscipline.ANIMALISM),
    CLAIRVOYANCE              ("Clairvoyance", DisplayableDiscipline.AUSPEX),
    PSYCHIC_ASSAULT           ("Psychic assault", DisplayableDiscipline.AUSPEX),
    QUICKNESS                 ("Quickness", DisplayableDiscipline.CELERITY),
    PROJECTILE                ("Projectile", DisplayableDiscipline.CELERITY),
    SHARED_NIGHTMARE          ("Shared Nightmare", DisplayableDiscipline.CHIMERSTRY),
    ARMY_OF_APPARITIONS       ("Army of Apparitions", DisplayableDiscipline.CHIMERSTRY),
    INFERNAL_COMPACT          ("Infernal Compact", DisplayableDiscipline.DAIMOINON),
    LINGERING_MALAISE         ("Lingering Malaise", DisplayableDiscipline.DEMENTATION),
    DENY                      ("Deny", DisplayableDiscipline.DEMENTATION),
    MASS_MANIPULATION         ("Mass Manipulation", DisplayableDiscipline.DOMINATE),
    TYRANTS_GAZE              ("Tyrant's Gaze", DisplayableDiscipline.DOMINATE),
    PERSONAL_ARMOR            ("Personal Armor", DisplayableDiscipline.FORTITUDE),
    REPAIR_THE_UNDEAD_FLESH   ("Repair the Undead Flesh", DisplayableDiscipline.FORTITUDE),
    SHATTERING_CRESCENDO      ("Shattering Crescendo", DisplayableDiscipline.MELPOMINEE),
    PERSISTENT_ECHO           ("Persistent Echo", DisplayableDiscipline.MELPOMINEE),
    STEAL_THE_MIND            ("Steal the Mind", DisplayableDiscipline.MYTHERCERIA),
    UNBURDEN_THE_BEASTIAL_SOUL("Unburden the Beastial Soul", DisplayableDiscipline.OBEAH),
    CACHE                     ("Cache", DisplayableDiscipline.OBFUSCATE),
    PHANTOM_HUNTER            ("Phantom Hunter", DisplayableDiscipline.OBFUSCATE),
    SHADOWSTEP                ("Shadowstep", DisplayableDiscipline.OBTENEBRATION),
    SHADOW_TWIN               ("Shadow Twin", DisplayableDiscipline.OBTENEBRATION),
    FORCE                     ("Force", DisplayableDiscipline.POTENCE),
    FLICK                     ("Flick", DisplayableDiscipline.POTENCE),
    PARALYZING_GLANCE         ("Paralyzing Glance", DisplayableDiscipline.PRESENCE),
    LOVE                      ("Love", DisplayableDiscipline.PRESENCE),
    EARTH_CONTROL             ("Earth Control", DisplayableDiscipline.PROTEAN),
    SHAPE_MASTERY             ("Shape Mastery", DisplayableDiscipline.PROTEAN),
    BLOOD_SWEAT               ("Blood Sweat", DisplayableDiscipline.QUIETUS),
    BAALS_BLOODY_TALONS       ("Baal's Bloody Talons", DisplayableDiscipline.QUIETUS),
    SEED_OF_CORRUPTION        ("Seed of Corruption", DisplayableDiscipline.SERPENTIS),
    DIVINE_IMAGE              ("Divine Image", DisplayableDiscipline.SERPENTIS),
    KISS_OF_LACHESIS          ("Kiss of Lachesis", DisplayableDiscipline.TEMPORSIS),
    CORRUPT_THE_FLESH         ("Corrupt the Flesh", DisplayableDiscipline.THANATOSIS),
    FIERY_AGONY               ("Fiery Agony", DisplayableDiscipline.VALEREN),
    BREATH_OF_THE_DRAGON      ("Breath of the Dragon", DisplayableDiscipline.VICISSITUDE),
    ACID_BLOOD                ("Acid Blood", DisplayableDiscipline.VICISSITUDE),
    BULWARK                   ("Bulwark", DisplayableDiscipline.VISCERATIKA),
    FURNACE_OF_STEEL          ("Furnace of Steel", DisplayableDiscipline.VISCERATIKA);

	private final String display;
	private final DisplayableDiscipline discipline;

	private DisplayableElderPower(String display, DisplayableDiscipline discipline) {
		this.display = display;
		this.discipline = discipline;
	}
	
	@Override
	public String display() {
		return display;
	}
	
	@Override
	public DisplayableTrait displayableTrait() {
		return discipline.toDisplayableTrait();
	}
}
