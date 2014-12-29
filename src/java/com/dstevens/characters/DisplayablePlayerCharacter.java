package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.players.Setting;

public class DisplayablePlayerCharacter {

	public final String id;
	public final String name;
	public final Setting setting;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacter() {
		this(null, null, null);
	}
    
    private DisplayablePlayerCharacter(String id, String name, Setting setting) {
		this.id = id;
		this.name = name;
		this.setting = setting;
    }
	
	public static Function<PlayerCharacter, DisplayablePlayerCharacter> fromCharacter() {
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter(t.getId(), t.getName(), null);
	}
	
}
