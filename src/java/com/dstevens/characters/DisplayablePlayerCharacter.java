package com.dstevens.characters;

import java.util.function.Function;

public class DisplayablePlayerCharacter {

	public static Function<PlayerCharacter, DisplayablePlayerCharacter> fromCharacter() {
		return (PlayerCharacter t) -> new DisplayablePlayerCharacter();
	}

	
	
}
