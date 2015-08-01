package com.dstevens.troupe;

import java.util.List;
import java.util.stream.Collectors;

import com.dstevens.character.DisplayableSetting;
import com.dstevens.character.Setting;

public class DisplayableVenue {

	public Integer id;
	public List<DisplayableSetting> settings;

	//Jackson only
	@Deprecated
	public DisplayableVenue() {
		this(null, null);
	}
	
	public DisplayableVenue(Integer id, List<DisplayableSetting> setings) {
		this.id = id;
		this.settings = setings;
	}
	
	public static DisplayableVenue listable(Venue v) {
		return new DisplayableVenue(v.getId(), null);
	}

	public static DisplayableVenue from(Venue v) {
		return new DisplayableVenue(v.getId(), v.getSettings().stream().map((Setting s) -> DisplayableSetting.from(s)).collect(Collectors.toList()));
	}
	
	public Venue to() {
		return Venue.values()[id];
	}
	
}
