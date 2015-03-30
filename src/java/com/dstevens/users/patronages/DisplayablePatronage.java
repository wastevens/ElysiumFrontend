package com.dstevens.users.patronages;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DisplayablePatronage implements Comparable<DisplayablePatronage> {

    public String membershipId;
	public String expiration;
	public Integer userId;
	public String originalUsername;
	public List<DisplayablePatronagePaymentReceipt> payments;
	
	public DisplayablePatronage() {
		this(null, null, null, null, null);
	}
	
	public DisplayablePatronage(String membershipId, String expiration, Integer userId, String originalUsername, List<DisplayablePatronagePaymentReceipt> payments) {
		this.membershipId = membershipId;
		this.expiration = expiration;
		this.userId = userId;
		this.originalUsername = originalUsername;
		this.payments = payments;
	}

	public static DisplayablePatronage from(Patronage patronage) {
		Integer id = null;
		if(patronage.getUser() != null) {
			id = patronage.getUser().getId();
		}
		return new DisplayablePatronage(patronage.displayMembershipId(), new SimpleDateFormat("yyyy-MM-dd").format(patronage.getExpiration()), id, patronage.getOriginalUsername(), 
				                        patronage.getPayments().stream().map((PatronagePaymentReceipt t) -> DisplayablePatronagePaymentReceipt.from(t)).collect(Collectors.toList()));
	}

	@Override
	public int compareTo(DisplayablePatronage that) {
		return Comparator.comparing((DisplayablePatronage t) -> t.expiration == null ? "" : t.expiration).
				thenComparing(Comparator.comparing((DisplayablePatronage t) -> t.membershipId)).
				compare(this, that);
	}
}
