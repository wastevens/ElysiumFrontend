package com.dstevens.characters;

import java.util.Optional;
import java.util.function.Function;

import com.google.gson.Gson;

public class DisplayablePlayerCharacter {

	public final String id;
	public final String name;
	public final int setting;
	public final int status;
	public final int approval;
	public final Integer clan;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacter() {
		this(null, null, -1, -1, -1, null);
	}
    
    private DisplayablePlayerCharacter(String id, String name, int setting, int status, int approval, Integer clan) {
		this.id = id;
		this.name = name;
		this.setting = setting;
		this.status = status;
		this.approval = approval;
		this.clan = clan;
    }
	
	public static Function<PlayerCharacter, DisplayablePlayerCharacter> fromCharacter() {
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter(t.getId(), t.getName(), 
																	 t.getSetting().ordinal(), 
				                                                     t.getCurrentStatus().status().ordinal(), 
				                                                     t.getApprovalStatus().ordinal(), 
				                                                     Optional.ofNullable(t.getClan()).map((Enum<?> e) -> e.ordinal()).orElse(null));
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
}
