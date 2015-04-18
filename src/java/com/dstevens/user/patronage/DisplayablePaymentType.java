package com.dstevens.user.patronage;

import com.google.gson.Gson;

public class DisplayablePaymentType {

	private final String name;
	private final int ordinal;
	
	public DisplayablePaymentType(String name, int ordinal) {
		this.name = name;
		this.ordinal = ordinal;
	}
	
	public String getName() {
		return name;
	}
	
	public int getOrdinal() {
		return ordinal;
	}
	
	public String serialized() {
		return new Gson().toJson(this);
	}
}
