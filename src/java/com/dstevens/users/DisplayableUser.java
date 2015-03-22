package com.dstevens.users;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.dstevens.collections.Sets.set;

public class DisplayableUser implements Comparable<DisplayableUser> {

	public final Integer id;
	public final String firstName;
	public final String lastName;
	public final String email;
	public final Set<Integer> roles;
	public final DisplayablePatronage patronage;
	
	//Jackson only
    @Deprecated
	private DisplayableUser() {
		this(null, null, null, null, set(), null);
	}
	
	private DisplayableUser(Integer id, String firstName, String lastName, String email, Set<Integer> roles, DisplayablePatronage patronage) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
		this.patronage = patronage;
	}
	
	public Set<Role> roles() {
		return roles.stream().map((Integer i) -> Role.values()[i]).collect(Collectors.toSet());
	}
	
	public static Function<User, DisplayableUser> fromUser() {
		return (User t) -> new DisplayableUser(t.getId(), t.getFirstName(), t.getLastName(), t.getEmail(), 
				                               t.getRoles().stream().map((Role r) -> r.ordinal()).collect(Collectors.toSet()),
				                               DisplayablePatronage.from(t.getPatronage()));
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Set<Integer> getRoles() {
		return roles;
	}

	@Override
	public int compareTo(DisplayableUser that) {
		return Comparator.comparing((DisplayableUser t) -> t.lastName == null ? "" : t.lastName).
			thenComparing(Comparator.comparing((DisplayableUser t) -> t.firstName == null ? "" : t.firstName)).
			thenComparing(Comparator.comparing((DisplayableUser t) -> t.email == null ? "" : t.email)).
			thenComparing(Comparator.comparing((DisplayableUser t) -> t.id)).
			compare(this, that);
	}
	
}
