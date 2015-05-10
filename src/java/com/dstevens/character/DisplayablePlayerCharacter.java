package com.dstevens.character;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dstevens.character.trait.background.CharacterBackground;
import com.dstevens.character.trait.distinction.flaw.CharacterFlaw;
import com.dstevens.character.trait.distinction.merit.CharacterMerit;
import com.dstevens.character.trait.power.discipline.CharacterDiscipline;
import com.dstevens.character.trait.power.discipline.ElderPower;
import com.dstevens.character.trait.power.discipline.Technique;
import com.dstevens.character.trait.power.magic.necromancy.NecromanticRitual;
import com.dstevens.character.trait.power.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.character.trait.skill.CharacterSkill;
import com.google.gson.Gson;

import static com.dstevens.collections.Lists.sort;

public class DisplayablePlayerCharacter {

	public final Integer id;
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
	public final List<DisplayablePlayerCharacterTrait> skills;
	public final List<DisplayablePlayerCharacterTrait> backgrounds;
	public final List<DisplayablePlayerCharacterTrait> disciplines;
	public final List<DisplayablePlayerCharacterTrait> techniques;
	public final List<DisplayablePlayerCharacterTrait> elderPowers;
	public final List<DisplayablePlayerCharacterTrait> necromanticRituals;
	public final List<DisplayablePlayerCharacterTrait> thaumaturgicalRituals;
	public final List<DisplayablePlayerCharacterTrait> merits;
	public final List<DisplayablePlayerCharacterTrait> flaws;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacter() {
		this(null, null, -1, -1, -1, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null);
	}
    
    private DisplayablePlayerCharacter(Integer id, String name, int setting, int status, int approval, Integer clan, Integer bloodline, List<Integer> inClanDisciplines,
    		                           int physicalAttribute, int socialAttribute, int mentalAttribute,
    		                           List<Integer> physicalAttributeFocuses, List<Integer> socialAttributeFocuses, List<Integer> mentalAttributeFocuses,
    		                           List<DisplayablePlayerCharacterTrait> skills, 
    		                           List<DisplayablePlayerCharacterTrait> backgrounds, 
    		                           List<DisplayablePlayerCharacterTrait> disciplines, 
    		                           List<DisplayablePlayerCharacterTrait> techniques, 
    		                           List<DisplayablePlayerCharacterTrait> elderPowers,
    		                           List<DisplayablePlayerCharacterTrait> necromanticRituals, 
    		                           List<DisplayablePlayerCharacterTrait> thaumaturgicalRituals,
    		                           List<DisplayablePlayerCharacterTrait> merits, 
    		                           List<DisplayablePlayerCharacterTrait> flaws) {
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
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter(t.getId(), t.getName(), 
																	 t.getSetting().getId(), 
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
				                                                     sort(t.getSkills().stream().map((CharacterSkill m) -> new DisplayablePlayerCharacterTrait(m.trait().ordinal(), m.rating(), m.getSpecialization(), m.getFocuses())).collect(Collectors.toList())),
				                                                     sort(t.getBackgrounds().stream().map((CharacterBackground m) -> new DisplayablePlayerCharacterTrait(m.trait().ordinal(), m.rating(), m.getSpecialization(), m.getFocuses())).collect(Collectors.toList())),
				                                                     sort(t.getDisciplines().stream().map((CharacterDiscipline m) -> new DisplayablePlayerCharacterTrait(m.trait().ordinal(), m.rating())).collect(Collectors.toList())),
				                                                     sort(t.getTechniques().stream().map((Technique m) -> new DisplayablePlayerCharacterTrait(m.ordinal())).collect(Collectors.toList())),
				                                                     sort(t.getElderPowers().stream().map((ElderPower m) -> new DisplayablePlayerCharacterTrait(m.ordinal())).collect(Collectors.toList())),
				                                                     sort(t.getNecromanticRituals().stream().map((NecromanticRitual m) -> new DisplayablePlayerCharacterTrait(m.ordinal())).collect(Collectors.toList())),
				                                                     sort(t.getThaumaturgicalRituals().stream().map((ThaumaturgicalRitual m) -> new DisplayablePlayerCharacterTrait(m.ordinal())).collect(Collectors.toList())),
				                                                     sort(t.getMerits().stream().map((CharacterMerit m) -> new DisplayablePlayerCharacterTrait(m.trait().ordinal(), m.getSpecialization())).collect(Collectors.toList())),
				                                                     sort(t.getFlaws().stream().map((CharacterFlaw m) -> new DisplayablePlayerCharacterTrait(m.trait().ordinal(), m.getSpecialization())).collect(Collectors.toList()))
				                                                     );
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
