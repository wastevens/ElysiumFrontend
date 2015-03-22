package com.dstevens.users;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ForeignKey;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dstevens.characters.PlayerCharacter;

import static com.dstevens.collections.Sets.set;
import static com.dstevens.collections.Sets.setWith;
import static com.dstevens.collections.Sets.setWithout;

@SuppressWarnings("deprecation")
@Entity
@Table(name="User")
@Component
@Scope("session")
public class User implements UserDetails {

	private static final long serialVersionUID = -7180299088295506267L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "user", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
    @Column(name="email")
    private final String email;
    
    @Column(name="password")
    private final String password;
    
	@ElementCollection
    @ForeignKey(name="User_Roles_FK")
    private final Set<Role> roles;

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_PlayerCharacters", 
	           joinColumns = @JoinColumn(name="user_id"), 
	           inverseJoinColumns = @JoinColumn(name="playerCharacter_id"))
	@ForeignKey(name="User_PlayerCharacters_FK", inverseName="PlayerCharacters_User_FK")
    private final Set<PlayerCharacter> characters;
	
    @Column(name="firstName")
    private final String firstName;
    
    @Column(name="lastName")
    private final String lastName;
	
    @OneToOne(optional=true)
	@JoinColumn(name="patronage_id")
	@ForeignKey(name="User_Patronage_FK")
    private final Patronage patronage;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
	private User() {
    	this(null, null, null, set(), set(), null, null, null);
    }
    
    public User(String email, String password, Set<Role> roles) {
    	this(null, email, password, roles, set(), null, null, null);
    }
    
    private User(Integer id, String email, String password, Set<Role> roles, Set<PlayerCharacter> characters, String firstName, String lastName, Patronage patronage) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.characters = characters;
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronage = patronage;
    }

	public Integer getId() {
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

    public Set<PlayerCharacter> getCharacters() {
    	return characters;
    }
    
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public User withEmail(String email) {
		return new User(id, email, password, roles, characters, firstName, lastName, patronage);
	}
	
	public User withPassword(String password) {
		return new User(id, email, password, roles, characters, firstName, lastName, patronage);
	}
	
	public User withRoles(Set<Role> roles) {
		return new User(id, email, password, roles, characters, firstName, lastName, patronage);
	}
	
	public User withRole(Role role) {
		return new User(id, email, password, setWith(roles, role), characters, firstName, lastName, patronage);
	}
	
	public User withoutRole(Role role) {
		return new User(id, email, password, setWithout(roles, role), characters, firstName, lastName, patronage);
	}
	
	public User withCharacter(PlayerCharacter character) {
		return new User(id, email, password, roles, setWith(characters, character), firstName, lastName, patronage);
	}
	
	public User withoutCharacter(PlayerCharacter character) {
		return new User(id, email, password, roles, setWithout(characters, character), firstName, lastName, patronage);
	}
	
	public User withFirstName(String firstName) {
		return new User(id, email, password, roles, characters, firstName, lastName, patronage);
	}
	
	public User withLastName(String lastName) {
		return new User(id, email, password, roles, characters, firstName, lastName, patronage);
	}
	
	public User withPatronage(Patronage patronage) {
		return new User(id, email, password, roles, characters, firstName, lastName, patronage);
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
	
	public boolean isPatronageActiveAsOf(Date date) {
		return patronage.isActiveAsOf(date);
	}
}
