package com.dstevens.characters;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import static com.dstevens.collections.Lists.sort;

public class DisplayablePlayerCharacter {

	public final String id;
	public final String name;
	public final int setting;
	public final int status;
	public final int approval;
	public final Integer clan;
	public final Integer bloodline;
	public final List<Integer> inClanDisciplines;
	public final int physicalAttribute;
	public final int socialAttribute;
	public final int mentalAttribute;
	public final List<Integer> physicalAttributeFocuses;
	public final List<Integer> socialAttributeFocuses;
	public final List<Integer> mentalAttributeFocuses;
	public final List<DisplayableCharacterSkill> skills;
	public final List<DisplayableCharacterBackground> backgrounds;
	public final List<DisplayableCharacterDiscipline> disciplines;
	public final List<DisplayableTechnique> techniques;
	public final List<DisplayableElderPower> elderPowers;
	public final List<DisplayableNecromanticRitual> necromanticRituals;
	public final List<DisplayableThaumaturgicalRitual> thaumaturgicalRituals;
	public final List<DisplayableCharacterMerit> merits;
	public final List<DisplayableCharacterFlaw> flaws;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacter() {
		this(null, null, -1, -1, -1, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null);
	}
    
    private DisplayablePlayerCharacter(String id, String name, int setting, int status, int approval, Integer clan, Integer bloodline, List<Integer> inClanDisciplines,
    		                           int physicalAttribute, int socialAttribute, int mentalAttribute,
    		                           List<Integer> physicalAttributeFocuses, List<Integer> socialAttributeFocuses, List<Integer> mentalAttributeFocuses,
    		                           List<DisplayableCharacterSkill> skills, List<DisplayableCharacterBackground> backgrounds, 
    		                           List<DisplayableCharacterDiscipline> disciplines, 
    		                           List<DisplayableTechnique> techniques, List<DisplayableElderPower> elderPowers,
    		                           List<DisplayableNecromanticRitual> necromanticRituals, List<DisplayableThaumaturgicalRitual> thaumaturgicalRituals,
    		                           List<DisplayableCharacterMerit> merits, List<DisplayableCharacterFlaw> flaws) {
		this.id = id;
		this.name = name;
		this.setting = setting;
		this.status = status;
		this.approval = approval;
		this.clan = clan;
		this.bloodline = bloodline;
		this.inClanDisciplines = inClanDisciplines;
		this.physicalAttribute = physicalAttribute;
		this.socialAttribute = socialAttribute;
		this.mentalAttribute = mentalAttribute;
		this.physicalAttributeFocuses = physicalAttributeFocuses;
		this.socialAttributeFocuses = socialAttributeFocuses;
		this.mentalAttributeFocuses = mentalAttributeFocuses;
		this.skills = skills;
		this.backgrounds = backgrounds;
		this.disciplines = disciplines;
		this.techniques = techniques;
		this.elderPowers = elderPowers;
		this.necromanticRituals = necromanticRituals;
		this.thaumaturgicalRituals = thaumaturgicalRituals;
		this.merits = merits;
		this.flaws = flaws;
    }
	
	public static Function<PlayerCharacter, DisplayablePlayerCharacter> fromCharacter() {
		DisplayableSpecializedCharacterTraitComparator displayableSpecializedCharacterTraitComparator = new DisplayableSpecializedCharacterTraitComparator();
		DisplayableCharacterTraitComparator displayableCharacterTraitComparator = new DisplayableCharacterTraitComparator();
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter(t.getId(), t.getName(), 
																	 t.getSetting().ordinal(), 
				                                                     t.getCurrentStatus().status().ordinal(), 
				                                                     t.getApprovalStatus().ordinal(), 
				                                                     Optional.ofNullable(t.getClan()).map((Enum<?> e) -> e.ordinal()).orElse(null),
				                                                     Optional.ofNullable(t.getBloodline()).map((Enum<?> e) -> e.ordinal()).orElse(null),
				                                                     t.getInClanDisciplines().stream().map((Enum<?> e) -> e.ordinal()).collect(Collectors.toList()),
				                                                     t.getPhysicalAttribute(),
				                                                     t.getSocialAttribute(),
				                                                     t.getMentalAttribute(),
				                                                     sort(t.getPhysicalAttributeFocuses().stream().map((Enum<?> e) -> e.ordinal()).collect(Collectors.toList())),
				                                                     sort(t.getSocialAttributeFocuses().stream().map((Enum<?> e) -> e.ordinal()).collect(Collectors.toList())),
				                                                     sort(t.getMentalAttributeFocuses().stream().map((Enum<?> e) -> e.ordinal()).collect(Collectors.toList())),
				                                                     sort(t.getSkills().stream().map(DisplayableCharacterSkill.fromSkill()).collect(Collectors.toList()), displayableSpecializedCharacterTraitComparator),
				                                                     sort(t.getBackgrounds().stream().map(DisplayableCharacterBackground.fromBackground()).collect(Collectors.toList()), displayableSpecializedCharacterTraitComparator),
				                                                     sort(t.getDisciplines().stream().map(DisplayableCharacterDiscipline.fromDiscipline()).collect(Collectors.toList()), displayableCharacterTraitComparator),
				                                                     sort(t.getTechniques().stream().map(DisplayableTechnique.fromTechnique()).collect(Collectors.toList()), displayableCharacterTraitComparator),
				                                                     sort(t.getElderPowers().stream().map(DisplayableElderPower.fromElderPower()).collect(Collectors.toList()), displayableCharacterTraitComparator),
				                                                     sort(t.getNecromanticRituals().stream().map(DisplayableNecromanticRitual.fromNecromanticRitual()).collect(Collectors.toList()), displayableCharacterTraitComparator),
				                                                     sort(t.getThaumaturgicalRituals().stream().map(DisplayableThaumaturgicalRitual.fromThaumaturgicalRitual()).collect(Collectors.toList()), displayableCharacterTraitComparator),
				                                                     sort(t.getMerits().stream().map(DisplayableCharacterMerit.fromMerit()).collect(Collectors.toList()), displayableSpecializedCharacterTraitComparator),
				                                                     sort(t.getFlaws().stream().map(DisplayableCharacterFlaw.fromFlaw()).collect(Collectors.toList()), displayableSpecializedCharacterTraitComparator)
				                                                     );
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
