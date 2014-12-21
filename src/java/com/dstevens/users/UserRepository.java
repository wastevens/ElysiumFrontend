package com.dstevens.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private UserDao dao;

	@Autowired
	public UserRepository(UserDao dao) {
		this.dao = dao;
	}

	public Iterable<User> findAllUndeleted() {
		return dao.findAll();
	}

	public User findUser(String id) {
		return dao.findOne(id);
	}

	public void save(User user) {
		dao.save(user);
	}
	
	
	
}
