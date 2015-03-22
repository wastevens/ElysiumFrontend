package com.dstevens.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Authorization {

	private final String username;
	private final String key;
	private final String expiration;

	public static Authorization from(String token) {
		String[] expectedTokens = token.split(":");
		String username = expectedTokens[0];
		String key = expectedTokens[1];
		String expiration = expectedTokens[2];
		return new Authorization(username, key, expiration);
	}
	
	public Authorization(String username, String key, String expiration) {
		this.username = username;
		this.key = key;
		this.expiration = expiration;
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

	public String getUsername() {
		return username;
	}

	public String expiration() {
		return expiration;
	}

	public String getToken() {
		return username + ":" + key + ":" + expiration;
	}
	
}
