package com.dstevens.users;

import static com.dstevens.collections.Sets.set;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.ForeignKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dstevens.suppliers.IdSupplier;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    @Column(name="firstName")
    private final String firstName;
    
    @Column(name="lastName")
    private final String lastName;
	
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
	private User() {
    	this(null, null, null, set(), null, null);
    }
    
    public User(String email, String password, Set<Role> roles) {
    	this(new IdSupplier().get(), email, password, roles, null, null);
    }
    
    private User(String id, String email, String password, Set<Role> roles, String firstName, String lastName) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
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
		return new User(id, email, password, roles, firstName, lastName);
	}
	
	public User withFirstName(String firstName) {
		return new User(id, email, password, roles, firstName, lastName);
	}
	
	public User withLastName(String lastName) {
		return new User(id, email, password, roles, firstName, lastName);
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
