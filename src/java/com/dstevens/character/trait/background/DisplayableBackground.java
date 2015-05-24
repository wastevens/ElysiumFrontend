package com.dstevens.character.trait.background;

import com.dstevens.character.trait.DisplayableDetailLevel;

public class DisplayableBackground implements Comparable<DisplayableBackground> {

	public Integer id;
	public DisplayableDetailLevel displayableDetailLevel;

	@Deprecated
	//Jackson only
	public DisplayableBackground() {
		this(null, null);
	}
	
	private DisplayableBackground(Integer id, DisplayableDetailLevel displayableDetailLevel) {
		this.id = id;
		this.displayableDetailLevel = displayableDetailLevel;
	}
	
	public static DisplayableBackground from(Background t) {
		return new DisplayableBackground(t.getId(), DisplayableDetailLevel.from(t.detailLevel()));
	}
	
	public Background to() {
		return Background.values()[id];
	}

	@Override
	public int compareTo(DisplayableBackground that) {
		return this.id - that.id;
	}
	
}
