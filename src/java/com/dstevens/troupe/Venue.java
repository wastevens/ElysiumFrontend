package com.dstevens.troupe;

import java.util.List;

import com.dstevens.character.Setting;
import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentityUtilities;

import static com.dstevens.collections.Lists.list;

public enum Venue implements Identified<Integer> {

    CAMARILLA_ANARCH(0, list (Setting.CAMARILLA, Setting.ANARCH, Setting.INDEPENDENT_ALLIANCE)),
    SABBAT(1, list(Setting.SABBAT));
	
    private final int id;
	private final List<Setting> settings;

	private Venue(int id, List<Setting> settings) {
		this.id = id;
		this.settings = settings;
    }

    
	public static Venue from(int id) {
		return IdentityUtilities.withId(id, Venue.values());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	public List<Setting> getSettings() {
		return settings;
	}
    
}
