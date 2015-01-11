package com.dstevens.characters;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class DisplayablePlayerCharacter {

	public final String id;
	public final String name;
	public final int setting;
	public final int status;
	public final int approval;
	public final Integer clan;
	public final Integer bloodline;
	public final List<Integer> inClanDisciplines;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacter() {
		this(null, null, -1, -1, -1, null, null, null);
	}
    
    private DisplayablePlayerCharacter(String id, String name, int setting, int status, int approval, Integer clan, Integer bloodline, List<Integer> inClanDisciplines) {
		this.id = id;
		this.name = name;
		this.setting = setting;
		this.status = status;
		this.approval = approval;
		this.clan = clan;
		this.bloodline = bloodline;
		this.inClanDisciplines = inClanDisciplines;
    }
	
	public static Function<PlayerCharacter, DisplayablePlayerCharacter> fromCharacter() {
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter(t.getId(), t.getName(), 
																	 t.getSetting().ordinal(), 
				                                                     t.getCurrentStatus().status().ordinal(), 
				                                                     t.getApprovalStatus().ordinal(), 
				                                                     Optional.ofNullable(t.getClan()).map((Enum<?> e) -> e.ordinal()).orElse(null),
				                                                     Optional.ofNullable(t.getBloodline()).map((Enum<?> e) -> e.ordinal()).orElse(null),
				                                                     t.getInClanDisciplines().stream().map((Enum<?> e) -> e.ordinal()).collect(Collectors.toList()));
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
