package com.dstevens.character.trait.power.discipline;

public class DisplayableElderPower {

	public Integer id;
	public DisplayableDiscipline discipline;

	@Deprecated
	//Jackson only
	public DisplayableElderPower() {
		this(null, null);
	}
	
	public DisplayableElderPower(Integer id, DisplayableDiscipline discipline) {
		this.id = id;
		this.discipline = discipline;
	}

	public static DisplayableElderPower from(ElderPower t) {
		return new DisplayableElderPower(t.getId(), DisplayableDiscipline.from(t.getPower()));
	}
	
	public ElderPower to() {
		return ElderPower.values()[id];
	}
	
}
