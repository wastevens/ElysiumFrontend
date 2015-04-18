package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.RatedTrait;
import com.google.gson.Gson;

public class DisplayableRatedTrait {

	private final int ordinal;
	private final int rating;
	
	//Jackson only
    @SuppressWarnings("unused")
	@Deprecated
	private DisplayableRatedTrait() {
		this(-1, -1);
	}
	
	public DisplayableRatedTrait(int ordinal, int rating) {
		this.ordinal = ordinal;
		this.rating = rating;
	}

	public static Function<? super RatedTrait, DisplayableRatedTrait> fromDiscipline() {
		return (RatedTrait t) -> new DisplayableRatedTrait(t.trait().ordinal(), t.rating());
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
	
	public int ordinal() {
		return ordinal;
	}

	public int rating() {
		return rating;
	}

}
