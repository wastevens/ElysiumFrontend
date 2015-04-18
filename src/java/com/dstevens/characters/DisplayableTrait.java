package com.dstevens.characters;

import java.util.function.Function;

import com.dstevens.characters.traits.RatedTrait;
import com.dstevens.characters.traits.Trait;
import com.google.gson.Gson;

public class DisplayableTrait implements Comparable<DisplayableTrait> {

	private final int ordinal;
	private final Integer rating;

	public DisplayableTrait(int ordinal, Integer rating) {
		this.ordinal = ordinal;
		this.rating = rating;
	}

	public static Function<? super Trait, DisplayableTrait> fromTrait() {
		return (Trait t) -> new DisplayableTrait(t.ordinal(), null);
	}
	
	public static Function<? super RatedTrait, DisplayableTrait> fromRatedTrait() {
		return (RatedTrait t) -> new DisplayableTrait(t.trait().ordinal(), t.rating());
	}
	
	public static Function<? super RatedTrait, DisplayableTrait> fromSpecializedTrait() {
		return (RatedTrait t) -> new DisplayableTrait(t.trait().ordinal(), t.rating());
	}
	
	public int ordinal() {
		return ordinal;
	}
	
	public Integer rating() {
		return rating;
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}

	@Override
	public int compareTo(DisplayableTrait o) {
		return ordinal - o.ordinal;
	}

}
