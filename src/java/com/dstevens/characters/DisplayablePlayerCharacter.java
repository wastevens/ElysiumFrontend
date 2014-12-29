package com.dstevens.characters;

import java.util.function.Function;

public class DisplayablePlayerCharacter {

	public final String id;
	public final String name;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacter() {
		this(null, null);
	}
    
    private DisplayablePlayerCharacter(String id, String name) {
		this.id = id;
		this.name = name;
    }
	
	public static Function<PlayerCharacter, DisplayablePlayerCharacter> fromCharacter() {
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter(t.getId(), t.getName());
	}
	
}
