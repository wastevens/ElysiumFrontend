package com.dstevens.web.user.payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GetExpressCheckoutDetailsResponse {

	private final Map<String, String> responseMap;

	public GetExpressCheckoutDetailsResponse(Map<String, String> responseMap) {
		this.responseMap = responseMap;
	}
	
	public String getEmail() {
		return responseMap.get("EMAIL");
	}
	
	public String getAmount() {
		return responseMap.get("AMT");
	}
	
	public String getConfirmationToken() {
		return responseMap.get("TOKEN");
	}
	
	public String getPayerId() {
		return responseMap.get("PAYERID");
	}
	
	public Date getTimestamp() {
		String timestamp = responseMap.get("TIMESTAMP");
		String timestampPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		try {
			return new SimpleDateFormat(timestampPattern).parse(timestamp);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could not parse " + timestamp + " with pattern " + timestampPattern, e);
		}
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
