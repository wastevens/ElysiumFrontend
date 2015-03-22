package com.dstevens.users;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
		return StreamSupport.stream(dao.findAll().spliterator(), false).filter((User t) -> t.getPatronage() == null).collect(Collectors.toList());
	}
	
	public Iterable<User> findPatrons() {
		return StreamSupport.stream(dao.findAll().spliterator(), false).filter((User t) -> t.getPatronage() != null).collect(Collectors.toList());
	}

	public User findUser(Integer id) {
		if(id != null) {
			return dao.findOne(id);
		}
		return null;
	}
	
	public User findUserWithEmail(String email) {
		return dao.findWithEmail(email);
	}

	public void save(User user) {
		dao.save(user);
	}
	
	
	
}
