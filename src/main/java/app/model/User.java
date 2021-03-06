package app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import app.model.DomainBase;

@Entity
@Table(name = "users")
public class User extends DomainBase {

  private static final long serialVersionUID = 2353528370345499815L;
  private Long id;
  private String username;
  private String password;
  private String email;
  private Date lastPasswordReset;
  private String authorities;
  private List<Contact> contacts=new ArrayList<Contact>();

  public User() {
    super();
  }

  public User(String username, String password, String email, Date lastPasswordReset, String authorities) {
    this.setUsername(username);
    this.setPassword(password);
    this.setEmail(email);
    this.setLastPasswordReset(lastPasswordReset);
    this.setAuthorities(authorities);
  }

  @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_seq")
	@SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "username", unique=true)
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password")
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "email", unique=true)
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "last_password_reset")
  public Date getLastPasswordReset() {
    return this.lastPasswordReset;
  }

  public void setLastPasswordReset(Date lastPasswordReset) {
    this.lastPasswordReset = lastPasswordReset;
  }

  @Column(name = "authorities")
  public String getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(String authorities) {
    this.authorities = authorities;
  }

  	@OneToMany(cascade = CascadeType.ALL, mappedBy ="user", orphanRemoval = true)
	public List<Contact> getContacts() {
		return contacts;
	}
  	
  	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}
}