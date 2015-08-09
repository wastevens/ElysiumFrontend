package com.dstevens.user;

import java.util.Date;

import com.dstevens.character.DisplayablePlayerCharacter;
import com.dstevens.character.status.DisplayablePlayerCharacterStatus;
import com.dstevens.troupe.DisplayableVenue;

public class DisplayablePlayerCharacterOwnership implements Comparable<DisplayablePlayerCharacterOwnership> {

	public Integer id;
	public DisplayableUser user;
	public DisplayablePlayerCharacter character;
	public DisplayableVenue venue;
	public DisplayablePlayerCharacterStatus status;
	
	//Jackson only
    @Deprecated
	private DisplayablePlayerCharacterOwnership() {
		this(null, null, null, null, null);
	}
	
	private DisplayablePlayerCharacterOwnership(Integer id, DisplayableUser user, DisplayablePlayerCharacter character, DisplayableVenue venue, DisplayablePlayerCharacterStatus status) {
		this.id = id;
		this.user = user;
		this.character = character;
		this.venue = venue;
		this.status = status;
	}
	
	public static DisplayablePlayerCharacterOwnership from(PlayerCharacterOwnership playerCharacterOwnership) {
		Integer id = playerCharacterOwnership.getId();
		DisplayableUser user = DisplayableUser.fromOn(playerCharacterOwnership.getUser(), new Date());
		DisplayablePlayerCharacter character = DisplayablePlayerCharacter.from(playerCharacterOwnership.getCharacter());
		DisplayableVenue venue = DisplayableVenue.from(playerCharacterOwnership.getVenue());
		DisplayablePlayerCharacterStatus status = DisplayablePlayerCharacterStatus.from(playerCharacterOwnership.getStatus());
		return new DisplayablePlayerCharacterOwnership(id, user, character, venue, status);
	}
	
	@Override
	public int compareTo(DisplayablePlayerCharacterOwnership that) {
		return this.id.compareTo(that.id);
	}
	
}
