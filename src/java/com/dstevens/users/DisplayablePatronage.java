package com.dstevens.users;

import java.text.SimpleDateFormat;


public class DisplayablePatronage {

    public String membershipId;
	public String expiration;
	public Integer userId;
	
	public DisplayablePatronage() {
		this(null, null, null);
	}
	
	public DisplayablePatronage(String membershipId, String expiration, Integer userId) {
		this.membershipId = membershipId;
		this.expiration = expiration;
		this.userId = userId;
	}

	public static DisplayablePatronage from(Patronage patronage) {
		Integer id = null;
		if(patronage.getUser() != null) {
			id = patronage.getUser().getId();
		}
		return new DisplayablePatronage(patronage.displayMembershipId(), new SimpleDateFormat("yyyy-MM-dd").format(patronage.getExpiration()), id);
	}
	
}
