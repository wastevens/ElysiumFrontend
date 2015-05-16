package com.dstevens.character.trait.attribute.focus;


public class DisplayablePhysicalAttributeFocus implements Comparable<DisplayablePhysicalAttributeFocus>{

	public Integer id;

	@Deprecated
	//Jackson only
	public DisplayablePhysicalAttributeFocus() {
		this(null);
	}
	
	private DisplayablePhysicalAttributeFocus(Integer id) {
		this.id = id;
	}
	
	public static DisplayablePhysicalAttributeFocus from(PhysicalAttributeFocus t) {
		return new DisplayablePhysicalAttributeFocus(t.getId());
	}
	
	public PhysicalAttributeFocus to() {
		return PhysicalAttributeFocus.values()[id];
	}

	@Override
	public int compareTo(DisplayablePhysicalAttributeFocus that) {
		return this.id - that.id;
	}
	
}
