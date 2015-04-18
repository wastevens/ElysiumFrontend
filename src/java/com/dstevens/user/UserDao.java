package com.dstevens.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
	
	@Query("SELECT a FROM User a WHERE a.email = ?1")
    User findWithEmail(String userEmail);

}
