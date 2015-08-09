package com.dstevens.character.status;


public class DisplayablePlayerCharacterStatus {

	public Integer id;

	//Jackson only
	@Deprecated
	public DisplayablePlayerCharacterStatus() {
		this(null);
	}
	
	public DisplayablePlayerCharacterStatus(Integer id) {
		this.id = id;
	}

	public static DisplayablePlayerCharacterStatus from(PlayerCharacterStatus s) {
		return new DisplayablePlayerCharacterStatus(s.getId());
	}
	
	public PlayerCharacterStatus to() {
		return PlayerCharacterStatus.values()[id];
	}

}
