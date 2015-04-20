package com.dstevens.web.trait.vampire;

import java.util.List;
import java.util.stream.Collectors;

import com.dstevens.web.trait.DisplayableTrait;
import com.dstevens.web.trait.DisplayableTraitSource;

import static com.dstevens.collections.Lists.list;

@SuppressWarnings("unchecked")
public enum DisplayableBloodline implements DisplayableTraitSource {

	ASSAMITE("None", list(DisplayableDiscipline.CELERITY), list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.QUIETUS)),
    VIZIER("Vizier", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.CELERITY), list(DisplayableDiscipline.QUIETUS)),
	SORCERER("Sorcerer", list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.QUIETUS), list(DisplayableDiscipline.LURE_OF_FLAMES),
			             list(DisplayableDiscipline.PATH_OF_BLOOD,            
			                  DisplayableDiscipline.PATH_OF_CONJURING,        
			                  DisplayableDiscipline.PATH_OF_CORRUPTION,       
			                  DisplayableDiscipline.PATH_OF_ELEMENTAL_MASTERY,
			                  DisplayableDiscipline.MOVEMENT_OF_THE_MIND,     
			                  DisplayableDiscipline.PATH_OF_TECHNOMANCY,      
			                  DisplayableDiscipline.PATH_OF_WEATHER_MASTERY)), 
	
	BRUJAH("None", list(DisplayableDiscipline.CELERITY), list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.PRESENCE)),
    TRUE_BRUJAH("True Brujah", list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.PRESENCE), list(DisplayableDiscipline.TEMPORSIS)),
    
    FOLLOWER_OF_SET("None", list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.PRESENCE), list(DisplayableDiscipline.SERPENTIS)),
    TLACLQUE("Tlaclque", list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.PRESENCE), list(DisplayableDiscipline.PROTEAN)), 
    VIPER("Viper", list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.PRESENCE), list(DisplayableDiscipline.SERPENTIS)),
    
    GANGREL("None", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.PROTEAN)),
    COYOTE("Coyote", list(DisplayableDiscipline.CELERITY), list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.PROTEAN)), 
    NOIAD("Noiad", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.PROTEAN)), 
    AHRIMANE("Ahrimane", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.PRESENCE), list(DisplayableDiscipline.PATH_OF_ELEMENTAL_MASTERY)),
    
    GIOVANNI("None", list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.SEPULCHRE_PATH)),
    PREMASCINE("Premascine", list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.SEPULCHRE_PATH)),
    
    LASOMBRA("None", list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.OBTENEBRATION)),
    KISAYD("Kisayd", list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.MYTHERCERIA), list(DisplayableDiscipline.OBTENEBRATION)),
    
    MALKAVIAN("None", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.DEMENTATION), list(DisplayableDiscipline.OBFUSCATE)),
    ANANKE("Ananke", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.DEMENTATION), list(DisplayableDiscipline.PRESENCE)),
    KNIGHT_OF_THE_MOON("Knight of the Moon", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.OBFUSCATE)),
    
    NOSFERATU("None", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.POTENCE)),
    
    TOREADOR("None", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.CELERITY), list(DisplayableDiscipline.PRESENCE)),
    ISHTARRI("Ishtarri", list(DisplayableDiscipline.CELERITY), list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.PRESENCE)),
    VOLGIRRE("Volgirre", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.CELERITY), list(DisplayableDiscipline.PRESENCE)),

    TREMERE("None", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.PATH_OF_BLOOD)),
    TELYAV("Telyav", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.PRESENCE), list(DisplayableDiscipline.PATH_OF_BLOOD)),

    TZIMISCE("None", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.VICISSITUDE)),
    CARPATHIAN("Carpathian", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.DOMINATE)),
    KOLDUN("Koldun", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.PATH_OF_ELEMENTAL_MASTERY),
    		         list(DisplayableDiscipline.PATH_OF_BLOOD,            
	                      DisplayableDiscipline.PATH_OF_CONJURING,        
	                      DisplayableDiscipline.PATH_OF_CORRUPTION,       
	                      DisplayableDiscipline.LURE_OF_FLAMES,
	                      DisplayableDiscipline.MOVEMENT_OF_THE_MIND,     
	                      DisplayableDiscipline.PATH_OF_TECHNOMANCY,      
	                      DisplayableDiscipline.PATH_OF_WEATHER_MASTERY)),

    VENTRUE("None", list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.PRESENCE)),
    CRUSADER("Crusader", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.FORTITUDE)),

    CATIFF("None", list(DisplayableDiscipline.ANIMALISM, DisplayableDiscipline.AUSPEX, DisplayableDiscipline.CELERITY, DisplayableDiscipline.DOMINATE, DisplayableDiscipline.FORTITUDE, DisplayableDiscipline.OBFUSCATE, DisplayableDiscipline.POTENCE, DisplayableDiscipline.PRESENCE),
    		       list(DisplayableDiscipline.ANIMALISM, DisplayableDiscipline.AUSPEX, DisplayableDiscipline.CELERITY, DisplayableDiscipline.DOMINATE, DisplayableDiscipline.FORTITUDE, DisplayableDiscipline.OBFUSCATE, DisplayableDiscipline.POTENCE, DisplayableDiscipline.PRESENCE),
    		       list(DisplayableDiscipline.ANIMALISM, DisplayableDiscipline.AUSPEX, DisplayableDiscipline.CELERITY, DisplayableDiscipline.DOMINATE, DisplayableDiscipline.FORTITUDE, DisplayableDiscipline.OBFUSCATE, DisplayableDiscipline.POTENCE, DisplayableDiscipline.PRESENCE)),
    
    BAALI("None", list(DisplayableDiscipline.DAIMOINON), list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.PRESENCE)),
    ANGELLIS_ATER("Angellis Ater", list(DisplayableDiscipline.DAIMOINON), list(DisplayableDiscipline.DOMINATE), list(DisplayableDiscipline.POTENCE, DisplayableDiscipline.PRESENCE, DisplayableDiscipline.OBFUSCATE)),

    CAPPADOCIAN("None", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.MORTIS_PATH)),
    SAMEDI("Samedi", list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.OBFUSCATE), list(DisplayableDiscipline.THANATOSIS)),
    LAMIA("Lamia", list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.MORTIS_PATH)),
    
    RAVNOS("None", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.CHIMERSTRY), list(DisplayableDiscipline.FORTITUDE)),
    BRAHMAN("Brahman", list(DisplayableDiscipline.ANIMALISM), list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.FORTITUDE)),                  
    
    SALUBRI("None", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.VALEREN)),
    HEALER("Healer", list(DisplayableDiscipline.AUSPEX), list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.OBEAH)),
    
    DAUGHTER_OF_CACOPHONY("None", list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.MELPOMINEE), list(DisplayableDiscipline.PRESENCE)),
    
    GARGOYLE("None", list(DisplayableDiscipline.FORTITUDE), list(DisplayableDiscipline.POTENCE), list(DisplayableDiscipline.VISCERATIKA)),
    ;

	private String display;
	private List<List<DisplayableDiscipline>> disciplines;

	private DisplayableBloodline(String display, List<DisplayableDiscipline>... disciplines) {
		this.display = display;
		this.disciplines = list(disciplines);
	}
	
	@Override
	public String display() {
		return display;
	}
	
	private List<List<DisplayableDiscipline>> disciplines() {
		return disciplines;
	}
	
	@Override
	public DisplayableTrait toDisplayableTrait() {
		return new DisplayableBloodlineTrait(display(), ordinal(), rating(), requiresSpecialization(), subcategory(), displayableTrait(), disciplines());
	}

	public static class DisplayableBloodlineTrait extends DisplayableTrait {

		public List<List<DisplayableTrait>> disciplines;

		public DisplayableBloodlineTrait(String name, int ordinal, Integer rating, Boolean requiresSpecialization, String category, DisplayableTrait displayableTrait, List<List<DisplayableDiscipline>> disciplines) {
			super(name, ordinal, rating, requiresSpecialization, category, displayableTrait);
			this.disciplines = disciplines.stream().map((List<DisplayableDiscipline> m) -> m.stream().map((DisplayableDiscipline t) -> t.toDisplayableTrait()).collect(Collectors.toList())).collect(Collectors.toList());
		}
		
	}
}
