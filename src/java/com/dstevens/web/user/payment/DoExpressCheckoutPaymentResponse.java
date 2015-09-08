package com.dstevens.web.user.payment;

import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DoExpressCheckoutPaymentResponse {

	private final Map<String, String> responseMap;

	public DoExpressCheckoutPaymentResponse(Map<String, String> responseMap) {
		this.responseMap = responseMap;
	}
	
	public String getEmail() {
		return responseMap.get("EMAIL");
	}
	
	public String getAmount() {
		return responseMap.get("PAYMENTINFO_0_AMT");
	}
	
	public String getPaypalIdentifier() {
		return responseMap.get("PAYMENTINFO_0_TRANSACTIONID");
	}
	
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
