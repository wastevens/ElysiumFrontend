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
	
	public Iterable<User> findClients() {
		return dao.findWithoutPatronage();
	}
	
	public Iterable<User> findPatrons() {
		return dao.findWithPatronage();
	}

	public User findUser(Integer id) {
		return dao.findOne(id);
	}
	
	public User findUserWithEmail(String email) {
		return dao.findWithEmail(email);
	}

	public void save(User user) {
		dao.save(user);
	}
	
	
	
}
