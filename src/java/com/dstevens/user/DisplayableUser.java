package com.dstevens.user;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.dstevens.user.patronage.DisplayablePatronage;

import static com.dstevens.collections.Sets.set;

public class DisplayableUser implements Comparable<DisplayableUser> {

	public final Integer id;
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String password;
	public final Set<DisplayableRole> roles;
	public final String membershipId;
	public final Boolean activePatron;
	public final DisplayablePatronage patronage;
	
	//Jackson only
    @Deprecated
	private DisplayableUser() {
		this(null, null, null, null, null, set(), null, null, null);
	}
	
	private DisplayableUser(Integer id, String firstName, String lastName, String email, String password, Set<DisplayableRole> roles, DisplayablePatronage patronage, String membershipId, Boolean activePatron) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.patronage = patronage;
		this.membershipId = membershipId;
		this.activePatron = activePatron;
	}
	
	public static DisplayableUser fromOn(User user, Date date) {
		DisplayablePatronage patronage = null;
		String membershipId = null;
		boolean activePatronage = false;
		if(user.getPatronage() != null) {
			membershipId = user.getPatronage().displayMembershipId();
			activePatronage = user.getPatronage().isActiveAsOf(date);
			patronage = DisplayablePatronage.fromOn(user.getPatronage(), date);
		}
		return new DisplayableUser(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), null,
				user.getRoles().stream().map((Role r) -> DisplayableRole.from(r)).collect(Collectors.toSet()),
				patronage, membershipId, activePatronage);
	}
	
	public static DisplayableUser listable(User user) {
		String membershipId = null;
		if(user.getPatronage() != null) {
			membershipId = user.getPatronage().displayMembershipId();
		}
		return new DisplayableUser(user.getId(), null, null, user.getEmail(), null, null, null, membershipId, null);
	}
	
	public User to() {
		return new User(email, password, roles.stream().map((DisplayableRole t) -> t.to()).collect(Collectors.toSet())).withFirstName(firstName).withLastName(lastName);
	}
	
	@Override
	public int compareTo(DisplayableUser that) {
		return Comparator.comparing((DisplayableUser t) -> t.lastName == null ? "" : t.lastName).
			thenComparing(Comparator.comparing((DisplayableUser t) -> t.email == null ? "" : t.email)).
			thenComparing(Comparator.comparing((DisplayableUser t) -> t.firstName == null ? "" : t.firstName)).
			thenComparing(Comparator.comparing((DisplayableUser t) -> t.id)).
			compare(this, that);
	}
	
}
