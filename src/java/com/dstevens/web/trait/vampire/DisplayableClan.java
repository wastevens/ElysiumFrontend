package com.dstevens.web.trait.vampire;

import java.util.List;
import java.util.stream.Collectors;

import com.dstevens.web.trait.DisplayableTrait;
import com.dstevens.web.trait.DisplayableTraitSource;

import static com.dstevens.collections.Lists.list;

public enum DisplayableClan implements DisplayableTraitSource {

	ASSAMITE("Assamite", list(DisplayableBloodline.ASSAMITE, DisplayableBloodline.VIZIER, DisplayableBloodline.SORCERER)),
	BRUJAH("Brujah", list(DisplayableBloodline.BRUJAH, DisplayableBloodline.TRUE_BRUJAH)),
    FOLLOWER_OF_SET("Follower of Set", list(DisplayableBloodline.FOLLOWER_OF_SET, DisplayableBloodline.TLACLQUE, DisplayableBloodline.VIPER)),
    GANGREL("Gangrel", list(DisplayableBloodline.GANGREL, DisplayableBloodline.COYOTE, DisplayableBloodline.NOIAD, DisplayableBloodline.AHRIMANE)),
    GIOVANNI("Giovanni", list(DisplayableBloodline.GIOVANNI, DisplayableBloodline.PREMASCINE)),
    LASOMBRA("Lasombra", list(DisplayableBloodline.LASOMBRA, DisplayableBloodline.KISAYD)),
    MALKAVIAN("Malkavian", list(DisplayableBloodline.MALKAVIAN, DisplayableBloodline.ANANKE, DisplayableBloodline.KNIGHT_OF_THE_MOON)),
    NOSFERATU("Nosferatu", list(DisplayableBloodline.NOSFERATU)),
    TOREADOR("Toreador", list(DisplayableBloodline.TOREADOR, DisplayableBloodline.ISHTARRI, DisplayableBloodline.VOLGIRRE)),
    TREMERE("Tremere", list(DisplayableBloodline.TREMERE, DisplayableBloodline.TELYAV)),
    TZIMISCE("Tzimisce", list(DisplayableBloodline.TZIMISCE, DisplayableBloodline.CARPATHIAN, DisplayableBloodline.KOLDUN)),
    VENTRUE("Ventrue", list(DisplayableBloodline.VENTRUE, DisplayableBloodline.CRUSADER)),
    CATIFF("Catiff", list(DisplayableBloodline.CATIFF)),
    BAALI("Baali", list(DisplayableBloodline.BAALI, DisplayableBloodline.ANGELLIS_ATER)),
    CAPPADOCIAN("Cappadocian", list(DisplayableBloodline.CAPPADOCIAN, DisplayableBloodline.SAMEDI, DisplayableBloodline.LAMIA)),
    RAVNOS("Ravnos", list(DisplayableBloodline.RAVNOS, DisplayableBloodline.BRAHMAN)),
    SALUBRI("Salubri", list(DisplayableBloodline.SALUBRI, DisplayableBloodline.HEALER)),
    DAUGHTER_OF_CACOPHONY("Daughter of Cacophony", list(DisplayableBloodline.DAUGHTER_OF_CACOPHONY)),
    GARGOYLE("Gargoyle", list(DisplayableBloodline.GARGOYLE)),
    ;

	private String display;
	private List<DisplayableBloodline> bloodlines;

	private DisplayableClan(String display, List<DisplayableBloodline> bloodlines) {
		this.display = display;
		this.bloodlines = bloodlines;
	}
	
	@Override
	public String display() {
		return display;
	}
	
	private List<DisplayableBloodline> bloodlines() {
		return bloodlines;
	}
	
	@Override
	public DisplayableTrait toDisplayableTrait() {
		return new DisplayableClanTrait(display(), ordinal(), rating(), requiresSpecialization(), subcategory(), displayableTrait(), bloodlines());
	}

	public static class DisplayableClanTrait extends DisplayableTrait {

		public List<DisplayableTrait> bloodlines;

		public DisplayableClanTrait(String name, int ordinal, Integer rating, Boolean requiresSpecialization, String category, DisplayableTrait displayableTrait, List<DisplayableBloodline> bloodlines) {
			super(name, ordinal, rating, requiresSpecialization, category, displayableTrait);
			this.bloodlines = bloodlines.stream().map((DisplayableBloodline t) -> t.toDisplayableTrait()).collect(Collectors.toList());
		}
		
	}
}
