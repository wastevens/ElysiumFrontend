package com.dstevens.user;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dstevens.user.guards.UserCreationGuard;
import com.dstevens.user.guards.UserInvalidException;

import static com.dstevens.collections.Sets.set;

@Repository
public class UserRepository {

	private final UserDao dao;
	private final Supplier<PasswordEncoder> passwordEncoderSupplier;
	private final List<UserCreationGuard> guards;

	@Autowired
	public UserRepository(UserDao dao, Supplier<PasswordEncoder> passwordEncoderSupplier, List<UserCreationGuard> guards) {
		this.dao = dao;
		this.passwordEncoderSupplier = passwordEncoderSupplier;
		this.guards = guards;
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
		User user = new User(email, passwordEncoderSupplier.get().encode(password), set(Role.USER)).withFirstName(firstName).withLastName(lastName);
		
		for (UserCreationGuard guard : guards) {
			guard.validate(user);
		}
		
		return save(user);
	}
}
