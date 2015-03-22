package com.dstevens.users;

import org.springframework.data.repository.CrudRepository;

public interface PatronageDao extends CrudRepository<Patronage, Integer> {
	
}
