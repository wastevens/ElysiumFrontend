package com.dstevens.characters;

import java.util.function.Function;

public class DisplayablePlayerCharacter {

	public final String id;
	public final String name;
	public final int setting;
	public final int status;
	public final int approval;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacter() {
		this(null, null, -1, -1, -1);
	}
    
    private DisplayablePlayerCharacter(String id, String name, int setting, int status, int approval) {
		this.id = id;
		this.name = name;
		this.setting = setting;
		this.status = status;
		this.approval = approval;
    }
	
	public static Function<PlayerCharacter, DisplayablePlayerCharacter> fromCharacter() {
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter(t.getId(), t.getName(), t.getSetting().ordinal(), t.getCurrentStatus().status().ordinal(), t.getApprovalStatus().ordinal());
	}
	
}
