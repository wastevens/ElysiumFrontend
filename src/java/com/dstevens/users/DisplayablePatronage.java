package com.dstevens.users;

import java.text.SimpleDateFormat;


public class DisplayablePatronage {

    public String membershipId;
	public String expiration;
	public Integer user_id;
	
	public DisplayablePatronage() {
		this(null, null, null);
	}
	
	public DisplayablePatronage(String membershipId, String expiration, Integer user_id) {
		this.membershipId = membershipId;
		this.expiration = expiration;
		this.user_id = user_id;
	}

	public static DisplayablePatronage from(Patronage patronage) {
		return new DisplayablePatronage(patronage.displayMembershipId(), new SimpleDateFormat("yyyy-MM-dd").format(patronage.getExpiration()), patronage.getUser().getId());
	}
	
}
