package app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact extends DomainBase {

  private static final long serialVersionUID = 2353528370345499815L;
  private Long id;
  private String first_name;
  private String last_name;
  private List<Phone> phones=new ArrayList<Phone>();
  private User user;

  public Contact() {
    super();
  }

  public Contact(String first_name, String last_name, User user) {
    this.setFirstName(first_name);
    this.setLastName(last_name);
    this.setUser(user);
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

  @Column(name = "first_name")
  public String getFirstName() {
    return this.first_name;
  }

  public void setFirstName(String first_name) {
    this.first_name = first_name;
  }

  @Column(name = "last_name")
  public String getLastName() {
    return this.last_name;
  }

  public void setLastName(String last_name) {
    this.last_name = last_name;
  }
  
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "contact_phone", joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "phone_id", referencedColumnName = "id"))
	public List<Phone> getPhones() {
		return phones;
	}
	
	public void addPhones(Phone phone) {
		this.phones.add(phone);
	}
	
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@ManyToOne
    @JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}