package com.dstevens.character.trait;


public class DisplayableDetailLevel {

	public Integer id;

	@Deprecated
	//Jackson only
	public DisplayableDetailLevel() {
		this(null);
	}
	
	private DisplayableDetailLevel(Integer id) {
		this.id = id;
	}
	
	public static DisplayableDetailLevel from(DetailLevel t) {
		return new DisplayableDetailLevel(t.getId());
	}
	
	public DetailLevel to() {
		return DetailLevel.values()[id];
	}
	
}
