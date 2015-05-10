package com.dstevens.user;

import com.dstevens.utilities.Identified;

public enum Role implements Identified<Integer> {

	ADMIN(0),
	USER(1);

	private final int id;

	private Role(int id) {
		this.id = id;		
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
}
