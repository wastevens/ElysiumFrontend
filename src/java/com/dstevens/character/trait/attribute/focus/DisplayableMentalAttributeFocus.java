package com.dstevens.character.trait.attribute.focus;


public class DisplayableMentalAttributeFocus implements Comparable<DisplayableMentalAttributeFocus> {

	public Integer id;

	@Deprecated
	//Jackson only
	public DisplayableMentalAttributeFocus() {
		this(null);
	}
	
	private DisplayableMentalAttributeFocus(Integer id) {
		this.id = id;
	}
	
	public static DisplayableMentalAttributeFocus from(MentalAttributeFocus t) {
		return new DisplayableMentalAttributeFocus(t.getId());
	}
	
	public MentalAttributeFocus to() {
		return MentalAttributeFocus.values()[id];
	}
	
	@Override
	public int compareTo(DisplayableMentalAttributeFocus that) {
		return this.id - that.id;
	}
}
