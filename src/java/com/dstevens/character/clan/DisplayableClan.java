package com.dstevens.character.clan;

import java.util.List;
import java.util.stream.Collectors;

public class DisplayableClan {

	public Integer id;
	public List<DisplayableBloodline> bloodlines;

	@Deprecated
	//Jackson only
	public DisplayableClan() {
		this(null, null);
	}
	
	public DisplayableClan(Integer id, List<DisplayableBloodline> bloodlines) {
		this.id = id;
		this.bloodlines = bloodlines;
	}

	public static DisplayableClan from(Clan t) {
		return new DisplayableClan(t.getId(), t.getBloodlines().stream().map((Bloodline b) -> DisplayableBloodline.from(b)).collect(Collectors.toList()));
	}
	
	public Clan to() {
		return Clan.values()[id];
	}
	
}
