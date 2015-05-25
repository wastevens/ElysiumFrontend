package com.dstevens.character.trait.distinction.merit;

import java.util.Optional;

import com.dstevens.character.DisplayableSetting;
import com.dstevens.character.Setting;
import com.dstevens.character.clan.Bloodline;
import com.dstevens.character.clan.Clan;
import com.dstevens.character.clan.DisplayableBloodline;
import com.dstevens.character.clan.DisplayableClan;
import com.dstevens.character.trait.distinction.DisplayableDistinctionType;


public class DisplayableMerit implements Comparable<DisplayableMerit> {

	public Integer id;
	public DisplayableDistinctionType type;
	public Integer points;
	public DisplayableClan clan;
	public DisplayableBloodline bloodline;
	public DisplayableSetting setting;

	@Deprecated
	//Jackson only
	public DisplayableMerit() {
		this(null, null, null, null, null, null);
	}
	
	private DisplayableMerit(Integer id, DisplayableDistinctionType type, Integer points, DisplayableClan clan, DisplayableBloodline bloodline, DisplayableSetting setting) {
		this.id = id;
		this.type = type;
		this.points = points;
		this.clan = clan;
		this.bloodline = bloodline;
		this.setting = setting;
	}
	
	public static DisplayableMerit from(Merit t) {
		return new DisplayableMerit(t.getId(), null, t.getPoints(), displayableClanFor(t), displayableBloodlineFor(t), displayableSettingFor(t));
	}

	private static DisplayableSetting displayableSettingFor(Merit t) {
		return Optional.ofNullable(t.getSetting()).flatMap((Setting c) -> Optional.of(DisplayableSetting.from(c))).orElse(null);
	}

	private static DisplayableBloodline displayableBloodlineFor(Merit t) {
		return Optional.ofNullable(t.getBloodline()).flatMap((Bloodline c) -> Optional.of(DisplayableBloodline.from(c))).orElse(null);
	}

	private static DisplayableClan displayableClanFor(Merit t) {
		return Optional.ofNullable(t.getClan()).flatMap((Clan c) -> Optional.of(DisplayableClan.from(c))).orElse(null);
	}
	
	public Merit to() {
		return Merit.values()[id];
	}

	@Override
	public int compareTo(DisplayableMerit that) {
		return this.id - that.id;
	}
	
}
