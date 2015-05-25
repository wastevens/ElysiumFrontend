package com.dstevens.character;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.clan.DisplayableBloodline;
import com.dstevens.character.clan.DisplayableClan;
import com.dstevens.character.trait.attribute.focus.DisplayableMentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.DisplayablePhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.DisplayableSocialAttributeFocus;
import com.dstevens.character.trait.attribute.focus.MentalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.PhysicalAttributeFocus;
import com.dstevens.character.trait.attribute.focus.SocialAttributeFocus;
import com.dstevens.character.trait.background.CharacterBackground;
import com.dstevens.character.trait.distinction.flaw.CharacterFlaw;
import com.dstevens.character.trait.distinction.merit.CharacterMerit;
import com.dstevens.character.trait.power.discipline.CharacterDiscipline;
import com.dstevens.character.trait.power.discipline.Discipline;
import com.dstevens.character.trait.power.discipline.DisplayableDiscipline;
import com.dstevens.character.trait.power.discipline.ElderPower;
import com.dstevens.character.trait.power.discipline.Technique;
import com.dstevens.character.trait.power.magic.necromancy.NecromanticRitual;
import com.dstevens.character.trait.power.magic.thaumaturgy.ThaumaturgicalRitual;
import com.dstevens.character.trait.skill.CharacterSkill;
import com.google.gson.Gson;

import static com.dstevens.collections.Lists.sort;

public class DisplayablePlayerCharacter {

	public Integer id;
	public String name;
	public DisplayableSetting setting;
	public int status;
	public int approval;
	public DisplayableClan clan;
	public DisplayableBloodline bloodline;
	public List<DisplayableDiscipline> inClanDisciplines;
	public int physicalAttribute;
	public int socialAttribute;
	public int mentalAttribute;
	public List<DisplayablePhysicalAttributeFocus> physicalAttributeFocuses;
	public List<DisplayableSocialAttributeFocus> socialAttributeFocuses;
	public List<DisplayableMentalAttributeFocus> mentalAttributeFocuses;
	public List<DisplayablePlayerCharacterTrait> skills;
	public List<DisplayablePlayerCharacterTrait> backgrounds;
	public List<DisplayablePlayerCharacterTrait> disciplines;
	public List<DisplayablePlayerCharacterTrait> techniques;
	public List<DisplayablePlayerCharacterTrait> elderPowers;
	public List<DisplayablePlayerCharacterTrait> necromanticRituals;
	public List<DisplayablePlayerCharacterTrait> thaumaturgicalRituals;
	public List<DisplayablePlayerCharacterTrait> merits;
	public List<DisplayablePlayerCharacterTrait> flaws;
	
	//Jackson only
    @Deprecated
	public DisplayablePlayerCharacter() {
		this(null, null, null, -1, -1, null, null, null, 0, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null);
	}
    
    public DisplayablePlayerCharacter(Integer id, String name, DisplayableSetting setting, int status, int approval, DisplayableClan clan, DisplayableBloodline bloodline, 
    		                          List<DisplayableDiscipline> inClanDisciplines,
    		                          int physicalAttribute, int socialAttribute, int mentalAttribute,
    		                          List<DisplayablePhysicalAttributeFocus> physicalAttributeFocuses, 
    		                          List<DisplayableSocialAttributeFocus> socialAttributeFocuses, 
    		                          List<DisplayableMentalAttributeFocus> mentalAttributeFocuses,
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
																	 DisplayableSetting.from(t.getSetting()), 
				                                                     t.getCurrentStatus().status().ordinal(), 
				                                                     t.getApprovalStatus().ordinal(), 
				                                                     Optional.ofNullable(t.getClan()).map((Clan c) -> DisplayableClan.from(c)).orElse(null),
				                                                     Optional.ofNullable(t.getBloodline()).map((Bloodline b) -> DisplayableBloodline.from(b)).orElse(null),
				                                                     t.getInClanDisciplines().stream().map((Discipline d) -> DisplayableDiscipline.from(d)).collect(Collectors.toList()),
				                                                     t.getPhysicalAttribute(),
				                                                     t.getSocialAttribute(),
				                                                     t.getMentalAttribute(),
				                                                     sort(t.getPhysicalAttributeFocuses().stream().map((PhysicalAttributeFocus f) -> DisplayablePhysicalAttributeFocus.from(f)).collect(Collectors.toList())),
				                                                     sort(t.getSocialAttributeFocuses().stream().map((SocialAttributeFocus f) -> DisplayableSocialAttributeFocus.from(f)).collect(Collectors.toList())),
				                                                     sort(t.getMentalAttributeFocuses().stream().map((MentalAttributeFocus f) -> DisplayableMentalAttributeFocus.from(f)).collect(Collectors.toList())),
				                                                     sort(t.getSkills().stream().map((CharacterSkill m) -> new DisplayablePlayerCharacterTrait(m.trait(), m.rating(), m.trait().detailLevel(), m.getSpecialization(), m.getFocuses())).collect(Collectors.toList())),
				                                                     sort(t.getBackgrounds().stream().map((CharacterBackground m) -> new DisplayablePlayerCharacterTrait(m.trait(), m.rating(), m.trait().detailLevel(), m.getSpecialization(), m.getFocuses())).collect(Collectors.toList())),
				                                                     sort(t.getDisciplines().stream().map((CharacterDiscipline m) -> new DisplayablePlayerCharacterTrait(m.trait(), m.rating())).collect(Collectors.toList())),
				                                                     sort(t.getTechniques().stream().map((Technique m) -> new DisplayablePlayerCharacterTrait(m)).collect(Collectors.toList())),
				                                                     sort(t.getElderPowers().stream().map((ElderPower m) -> new DisplayablePlayerCharacterTrait(m)).collect(Collectors.toList())),
				                                                     sort(t.getNecromanticRituals().stream().map((NecromanticRitual m) -> new DisplayablePlayerCharacterTrait(m)).collect(Collectors.toList())),
				                                                     sort(t.getThaumaturgicalRituals().stream().map((ThaumaturgicalRitual m) -> new DisplayablePlayerCharacterTrait(m)).collect(Collectors.toList())),
				                                                     sort(t.getMerits().stream().map((CharacterMerit m) -> new DisplayablePlayerCharacterTrait(m.trait(), null, m.getSpecialization())).collect(Collectors.toList())),
				                                                     sort(t.getFlaws().stream().map((CharacterFlaw m) -> new DisplayablePlayerCharacterTrait(m.trait(), null, m.getSpecialization())).collect(Collectors.toList()))
				                                                     );
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
