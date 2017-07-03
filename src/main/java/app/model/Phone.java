package app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phones")
public class Phone extends DomainBase {

  private static final long serialVersionUID = 2353528370345499815L;
  private Long id;
  private String number;
  private List<Contact> contacts = new ArrayList<Contact>();

  public Phone() {
    super();
  }

  public Phone(String number) {
    this.setNumber(number);
  }

  @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_seq")
	@SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
  @JsonIgnore
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

	@Column(name = "number")
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	@ManyToMany(mappedBy = "phones")
	@JsonIgnore
	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	@Override
	public String toString() {
		return "\"" + this.number + "\"";
	}
}