package com.dstevens.users;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dstevens.characters.DisplayablePlayerCharacter;

public class DisplayableUser {

	public String id;
	public String firstName;
	public String lastName;
	public String email;
	public Set<Integer> roles;
	public Set<DisplayablePlayerCharacter> characters;
	
	private DisplayableUser(String id, String firstName, String lastName, String email, Set<Integer> roles, Set<DisplayablePlayerCharacter> characters) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
		this.characters = characters;
	}
	
	public static Function<User, DisplayableUser> fromUser() {
		return (User t) -> new DisplayableUser(t.getId(), t.getFirstName(), t.getLastName(), t.getEmail(), 
				                               t.getRoles().stream().map((Role r) -> r.ordinal()).collect(Collectors.toSet()), 
				                               t.getCharacters().stream().map(DisplayablePlayerCharacter.fromCharacter()).collect(Collectors.toSet()));
	}
	
}
