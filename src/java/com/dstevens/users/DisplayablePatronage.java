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
		return new DisplayablePatronage(patronage.displayMembershipId(), new SimpleDateFormat("yyyy-MM-dd").format(patronage.getExpiration()), patronage.getUser().getId());
	}
	
}
