package com.dstevens.character;

public class DisplayableSetting {

	public Integer id;

	//Jackson only
	@Deprecated
	public DisplayableSetting() {
		this(null);
	}
	
	public DisplayableSetting(Integer id) {
		this.id = id;
	}

	public static DisplayableSetting from(Setting s) {
		return new DisplayableSetting(s.getId());
	}
	
	public Setting to() {
		return Setting.values()[id];
	}
	
}
