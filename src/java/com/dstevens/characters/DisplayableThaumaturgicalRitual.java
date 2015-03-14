package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.powers.magic.thaumaturgy.ThaumaturgicalRitual;
import com.google.gson.Gson;

public class DisplayableThaumaturgicalRitual implements DisplayableCharacterTrait {

	public final int ordinal;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableThaumaturgicalRitual() {
		this(-1);
	}
	
	public DisplayableThaumaturgicalRitual(int ordinal) {
		this.ordinal = ordinal;
	}

	public static Function<ThaumaturgicalRitual, DisplayableThaumaturgicalRitual> fromThaumaturgicalRitual() {
		return (ThaumaturgicalRitual t) -> new DisplayableThaumaturgicalRitual(t.ordinal());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
	@Override
	public int ordinal() {
		return ordinal;
	}
}
