package com.dstevens.users;

import java.text.SimpleDateFormat;
import java.util.Comparator;


public class DisplayablePatronage implements Comparable<DisplayablePatronage> {

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

	@Override
	public int compareTo(DisplayablePatronage that) {
		return Comparator.comparing((DisplayablePatronage t) -> t.expiration == null ? "" : t.expiration).
				thenComparing(Comparator.comparing((DisplayablePatronage t) -> t.membershipId)).
				compare(this, that);
	}
}
