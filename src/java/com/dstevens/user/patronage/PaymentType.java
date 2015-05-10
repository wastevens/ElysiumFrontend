package com.dstevens.user.patronage;

import com.dstevens.utilities.Identified;


public enum PaymentType implements Identified<Integer> {

	OTHER(0),
	PAYPAL(1),
	AWARD(2),
	GIFT(3)
	;
	

	public static PaymentType forId(int id) {
		for (PaymentType paymentType : values()) {
			if(paymentType.id == id) {
				return paymentType;
			}
		}
		throw new IllegalArgumentException("No payment type found for id " + id);
	}
	
	private final int id;

	private PaymentType(int id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}
}
