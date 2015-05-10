package com.dstevens.character.trait.power;

public class DisplayablePowerType {

	public Integer id;

	@Deprecated
	//Jackson only
	public DisplayablePowerType() {
		this(null);
	}
	
	private DisplayablePowerType(Integer id) {
		this.id = id;
	}
	
	public static DisplayablePowerType from(PowerType t) {
		return new DisplayablePowerType(t.getId());
	}
	
	public PowerType to() {
		return PowerType.values()[id];
	}
	
}
