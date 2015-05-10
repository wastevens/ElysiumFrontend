package com.dstevens.user.patronage;

public class DisplayablePaymentType {

	public int id;
	
	public DisplayablePaymentType() {
		this(-1);
	}
	
	public DisplayablePaymentType(int id) {
		this.id = id;
	}
	
	public static DisplayablePaymentType from(PaymentType t) {
		return new DisplayablePaymentType(t.getId());
	}
	
	public PaymentType to() {
		return PaymentType.forId(id);
	}
	
}
