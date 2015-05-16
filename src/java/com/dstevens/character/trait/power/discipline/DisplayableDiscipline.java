package com.dstevens.character.trait.power.discipline;

import com.dstevens.character.trait.power.DisplayablePowerType;

public class DisplayableDiscipline implements Comparable<DisplayableDiscipline> {

	public Integer id;
	public DisplayablePowerType powerType;

	@Deprecated
	//Jackson only
	public DisplayableDiscipline() {
		this(null, null);
	}
	
	private DisplayableDiscipline(Integer id, DisplayablePowerType powerType) {
		this.id = id;
		this.powerType = powerType;
	}
	
	public static DisplayableDiscipline from(Discipline t) {
		return new DisplayableDiscipline(t.getId(), DisplayablePowerType.from(t.powerType()));
	}
	
	public Discipline to() {
		return Discipline.values()[id];
	}

	@Override
	public int compareTo(DisplayableDiscipline that) {
		return this.id - that.id;
	}
}
