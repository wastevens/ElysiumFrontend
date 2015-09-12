package com.dstevens.user;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dstevens.user.guards.UserInvalidException;

@Repository
public class UserRepository {

	private final UserDao dao;
	private final UserCreator creator;

	@Autowired
	public UserRepository(UserDao dao, UserCreator userCreator) {
		this.dao = dao;
		this.creator = userCreator;
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

	public User save(User user) {
		return dao.save(user);
	}
	
	public User create(String email, String password, String firstName, String lastName) throws UserInvalidException {
		return creator.create(email, password, firstName, lastName);
	}
	
	
}
