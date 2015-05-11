package com.dstevens.character.trait.power.magic.necromancy;


public class DisplayableNecromanticRitual {

	public Integer id;
	public Integer rating;

	@Deprecated
	//Jackson only
	public DisplayableNecromanticRitual() {
		this(null, null);
	}
	
	public DisplayableNecromanticRitual(Integer id, Integer rating) {
		this.id = id;
		this.rating = rating;
	}
	
	public static DisplayableNecromanticRitual from(NecromanticRitual t) {
		return new DisplayableNecromanticRitual(t.getId(), t.rating());
	}
	
	public NecromanticRitual to() {
		return NecromanticRitual.values()[id];
	}
	
}
