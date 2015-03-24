package com.dstevens.users;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.dstevens.collections.Sets.set;

public class DisplayableUser implements Comparable<DisplayableUser> {

	public final Integer id;
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String password;
	public final Set<Integer> roles;
	public final String membershipId;
	public final Boolean activePatron;
	
	//Jackson only
    @Deprecated
	private DisplayableUser() {
		this(null, null, null, null, null, set(), null, null);
	}
	
	private DisplayableUser(Integer id, String firstName, String lastName, String email, String password, Set<Integer> roles, String membershipId, Boolean activePatron) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.membershipId = membershipId;
		this.activePatron = activePatron;
	}
	
	public Set<Role> roles() {
		return roles.stream().map((Integer i) -> Role.values()[i]).collect(Collectors.toSet());
	}
	
	public static Function<User, DisplayableUser> fromUserOn(Date date) {
		return new Function<User, DisplayableUser>() {
			@Override
			public DisplayableUser apply(User t) {
				String membershipId = null;
				boolean activePatronage = false;
				if(t.getPatronage() != null) {
					membershipId = t.getPatronage().displayMembershipId();
					activePatronage = t.getPatronage().isActiveAsOf(date);
				}
				return new DisplayableUser(t.getId(), t.getFirstName(), t.getLastName(), t.getEmail(), null,
						                   t.getRoles().stream().map((Role r) -> r.ordinal()).collect(Collectors.toSet()),
						                   membershipId, activePatronage);
			}
		};
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

	public String getPassword() {
		return password;
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
