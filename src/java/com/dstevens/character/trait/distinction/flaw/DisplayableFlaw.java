package com.dstevens.character.trait.distinction.flaw;

import java.util.Optional;

import com.dstevens.character.DisplayableSetting;
import com.dstevens.character.Setting;
import com.dstevens.character.trait.distinction.DisplayableDistinctionType;
import com.dstevens.character.trait.distinction.merit.Merit;

public class DisplayableFlaw {

	public Integer id;
	public DisplayableDistinctionType type;
	public Integer points;
	public DisplayableSetting setting;

	@Deprecated
	//Jackson only
	public DisplayableFlaw() {
		this(null, null, null, null);
	}
	
	private DisplayableFlaw(Integer id, DisplayableDistinctionType type, Integer points, DisplayableSetting setting) {
		this.id = id;
		this.type = type;
		this.points = points;
		this.setting = setting;
	}
	
	public static DisplayableFlaw from(Flaw t) {
		return new DisplayableFlaw(t.getId(), null, t.getPoints(), displayableSettingFor(t));
	}

	private static DisplayableSetting displayableSettingFor(Flaw t) {
		return Optional.ofNullable(t.getSetting()).flatMap((Setting c) -> Optional.of(DisplayableSetting.from(c))).orElse(null);
	}

	public Merit to() {
		return Merit.values()[id];
	}
	
}
