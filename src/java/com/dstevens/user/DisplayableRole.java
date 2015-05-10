package com.dstevens.user;


public class DisplayableRole {

	public Integer id;

	//Jackson only
	@Deprecated
	public DisplayableRole() {
		this(null);
	}
	
	public DisplayableRole(Integer id) {
		this.id = id;
	}

	public static DisplayableRole from(Role t) {
		return new DisplayableRole(t.getId());
	}
	
	public Role to() {
		return Role.values()[id];
	}
	
}
