package com.dstevens.user;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dstevens.character.PlayerCharacter;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.utilities.Identified;
import com.dstevens.utilities.IdentifiedObjectExtensions;

import static com.dstevens.collections.Sets.set;
import static com.dstevens.collections.Sets.setWith;
import static com.dstevens.collections.Sets.setWithout;

@SuppressWarnings("deprecation")
@Entity
@Table(name="User")
@Component
@Scope("session")
public class User implements UserDetails, Identified<Integer> {

	private static final long serialVersionUID = -7180299088295506267L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGen")
	@TableGenerator(name = "tableGen", pkColumnValue = "user", table="ID_Sequences", allocationSize=1 )
	@Column(name="id", nullable=false, unique=true)
    private final Integer id;
    
    @Column(name="email")
    private final String email;
    
    @Column(name="password")
    private String password;
    
	@ElementCollection
    @ForeignKey(name="User_Roles_FK")
    private Set<Role> roles;

	@OneToMany(cascade={CascadeType.ALL}, mappedBy="user")
    private Set<PlayerCharacterOwnership> characters;
	
    @Column(name="firstName")
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;
	
    @OneToOne(cascade={CascadeType.ALL}, optional=true)
    @JoinTable(name="User_Patronage", 
               joinColumns = @JoinColumn(name="user_id", nullable=true), 
               inverseJoinColumns = @JoinColumn(name="patronage_id", nullable=true),
               uniqueConstraints={@UniqueConstraint(columnNames={"patronage_id", "user_id"}, name="User_Patronage_UC")})
    @ForeignKey(name="User_Patronage_FK", inverseName="Patronage_User_FK")
    private Patronage patronage;
    
    //Hibernate only
    @SuppressWarnings("unused")
    @Deprecated
	private User() {
    	this(null, null, null, set(), set(), null, null, null);
    }
    
    public User(String email, String password, Set<Role> roles) {
    	this(null, email, password, roles, set(), null, null, null);
    }
    
    private User(Integer id, String email, String password, Set<Role> roles, Set<PlayerCharacterOwnership> characters, String firstName, String lastName, Patronage patronage) {
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
    	return characters.stream().map((PlayerCharacterOwnership p) -> p.getCharacter()).collect(Collectors.toSet()); 
    }
    
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Patronage getPatronage() {
		return patronage;
	}
	
	public boolean matchingPatronage(Patronage patronage) {
		if(this.patronage == null || patronage == null) {
			return this.patronage == patronage;
		}
		return (this.patronage.getId() == patronage.getId());
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
	
	public User withCharacter(PlayerCharacterOwnership character) {
		return new User(id, email, password, roles, setWith(characters, character), firstName, lastName, patronage);
	}
	
	public User withoutCharacter(PlayerCharacterOwnership character) {
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
	
	@Override
	public boolean equals(Object that) {
		return IdentifiedObjectExtensions.equals(this, that);
	}
	
	@Override
	public int hashCode() {
		return IdentifiedObjectExtensions.hashCodeFor(this);
	}
	
	@Override
	public String toString() {
		return IdentifiedObjectExtensions.toStringFor(this);
	}
}
