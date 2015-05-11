package com.dstevens.character.trait.power.magic.thaumaturgy;

public class DisplayableThaumaturgicalRitual {

	public Integer id;
	public Integer rating;

	@Deprecated
	//Jackson only
	public DisplayableThaumaturgicalRitual() {
		this(null, null);
	}
	
	public DisplayableThaumaturgicalRitual(Integer id, Integer rating) {
		this.id = id;
		this.rating = rating;
	}
	
	public static DisplayableThaumaturgicalRitual from(ThaumaturgicalRitual t) {
		return new DisplayableThaumaturgicalRitual(t.getId(), t.rating());
	}
	
	public ThaumaturgicalRitual to() {
		return ThaumaturgicalRitual.values()[id];
	}
	
}
