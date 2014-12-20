package com.dstevens.users;

import static com.dstevens.collections.Sets.set;
import static com.dstevens.collections.Sets.setWith;
import static com.dstevens.collections.Sets.setWithout;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.ForeignKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.suppliers.IdSupplier;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
@SuppressWarnings("deprecation")
public class User implements UserDetails {

	private static final long serialVersionUID = -7180299088295506267L;

	@Id
    private final String id;
    
    @Column(name="email")
    private final String email;
    
    @Column(name="password")
    private final String password;
    
	@ElementCollection
    @ForeignKey(name="User_Roles_FK")
    private final Set<Role> roles;

	@OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="troupe_id", referencedColumnName="id")
    @ForeignKey(name="Troupe_PlayerCharacters_FK")
    private final Set<PlayerCharacter> characters;
	
    @Column(name="firstName")
    private final String firstName;
    
    @Column(name="lastName")
    private final String lastName;
	
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
	private User() {
    	this(null, null, null, set(), set(), null, null);
    }
    
    public User(String email, String password, Set<Role> roles) {
    	this(new IdSupplier().get(), email, password, roles, set(), null, null);
    }
    
    private User(String id, String email, String password, Set<Role> roles, Set<PlayerCharacter> characters, String firstName, String lastName) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.characters = characters;
		this.firstName = firstName;
		this.lastName = lastName;
    }

	public String getId() {
		return id;
	}
    
    public String getEmail() {
		return email;
	}
    
    public String getPassword() {
    	return password;
    }
    
    public Set<Role> getRoles() {
		return roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public User withPassword(String password) {
		return new User(id, email, password, roles, characters, firstName, lastName);
	}
	
	public User withRole(Role role) {
		return new User(id, email, password, setWith(roles, role), characters, firstName, lastName);
	}
	
	public User withoutRole(Role role) {
		return new User(id, email, password, setWithout(roles, role), characters, firstName, lastName);
	}
	
	public User withCharacter(PlayerCharacter character) {
		return new User(id, email, password, roles, setWith(characters, character), firstName, lastName);
	}
	
	public User withFirstName(String firstName) {
		return new User(id, email, password, roles, characters, firstName, lastName);
	}
	
	public User withLastName(String lastName) {
		return new User(id, email, password, roles, characters, firstName, lastName);
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoles().stream().map((Role r) -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
