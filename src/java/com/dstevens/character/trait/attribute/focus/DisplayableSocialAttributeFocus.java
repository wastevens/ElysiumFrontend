package com.dstevens.character.trait.attribute.focus;


public class DisplayableSocialAttributeFocus implements Comparable<DisplayableSocialAttributeFocus> {

	public Integer id;

	@Deprecated
	//Jackson only
	public DisplayableSocialAttributeFocus() {
		this(null);
	}
	
	private DisplayableSocialAttributeFocus(Integer id) {
		this.id = id;
	}
	
	public static DisplayableSocialAttributeFocus from(SocialAttributeFocus t) {
		return new DisplayableSocialAttributeFocus(t.getId());
	}
	
	public SocialAttributeFocus to() {
		return SocialAttributeFocus.values()[id];
	}
	
	@Override
	public int compareTo(DisplayableSocialAttributeFocus that) {
		return this.id - that.id;
	}
}
