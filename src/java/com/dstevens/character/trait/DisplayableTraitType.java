package com.dstevens.character.trait;

public class DisplayableTraitType {

	public Integer id;

	public DisplayableTraitType() {
		this(null);
	}
	
	public DisplayableTraitType(Integer id) {
		this.id = id;
	}
	
	public static DisplayableTraitType from(TraitType t) {
		return new DisplayableTraitType(t.getId());
	}
	
	public TraitType to() {
		return TraitType.values()[id];
	}
	
}
