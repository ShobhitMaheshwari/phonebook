package app.controller;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.Application;
import app.model.CerberusUser;
import app.model.Contact;
import app.model.Phone;
import app.model.User;
import app.repository.ContactRepository;
import app.repository.PhoneRepository;
import app.repository.UserRepository;
import app.request.ContactRequest;
import app.request.PhoneRequest;

@RestController
@RequestMapping("${cerberus.route.contacts}")

public class ContactController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ContactController.class);

  @RequestMapping(method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('USER')")
  public ResponseEntity<?> getContacts() {
	  CerberusUser user = (CerberusUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user2 = repository.findOne(user.getId());
      
    return ResponseEntity.ok(user2.getContacts());
  }
  
  @PreAuthorize("hasAuthority('USER')")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> CreateContact(@RequestBody ContactRequest contact) {
	  CerberusUser user = (CerberusUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user2 = repository.findOne(user.getId());
      Contact contact2 = new Contact(contact.getFirst_name(), contact.getLast_name(), user2);
	  
	  contact2.setUser(user2);
	  contact2 = contactRepository.save(contact2);
	  return new ResponseEntity<>(new ContactResponse(contact2.getId()), HttpStatus.CREATED);
  }
  
  @PreAuthorize("hasAuthority('USER')")
  @RequestMapping(path="/{contacts_id}", method = RequestMethod.POST)
  public ResponseEntity<?> UpdateContact(@PathVariable(value="contacts_id") Long contacts_id, @RequestBody ContactRequest contactrequest) {
	  CerberusUser user = (CerberusUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user2 = repository.findOne(user.getId());
      
      Contact contact = contactRepository.findOne(contacts_id);
      if(contact.getUser().getId().equals(user2.getId())) {
    	  contact.setFirstName(contactrequest.getFirst_name());
		  contact.setLastName(contactrequest.getLast_name());
      }
      contactRepository.save(contact);
      
	  return new ResponseEntity<>(new ContactResponse(contacts_id), HttpStatus.OK);
  }
  
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(path="/{contacts_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> DeleteContact(@PathVariable(value="contacts_id") Long contacts_id) {
		CerberusUser user = (CerberusUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user2 = repository.findOne(user.getId());
      
		Contact contact = contactRepository.findOne(contacts_id);
		if(contact.getUser().getId().equals(user2.getId())) {
			temp(contact);
			contactRepository.delete(contacts_id);
			List<Phone> phones = contact.getPhones();
			for(Phone phone: phones) {
				phone = phoneRepository.findOne(phone.getId());
				if(phone.getContacts().size() == 0) {
					phoneRepository.delete(phone.getId());
				}
			}
		}
		return ResponseEntity.ok("{}");
	}
	@Transactional
	public void temp(Contact contact) {
		Hibernate.initialize(contact.getPhones());
	}
  
  @PreAuthorize("hasAuthority('USER')")
  @RequestMapping(path="/{contacts_id}/entries", method = RequestMethod.POST)
  public ResponseEntity<?> UpdatePhone(@PathVariable(value="contacts_id") Long contacts_id, @RequestBody PhoneRequest phoneRequest) {
	  CerberusUser user = (CerberusUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user2 = repository.findOne(user.getId());
      Contact contact = contactRepository.findOne(contacts_id);
      if(contact.getUser().getId().equals(user2.getId())) {
    	  contact.addPhones(new Phone(phoneRequest.getPhone()));
		  contactRepository.save(contact);
      }
      return new ResponseEntity<>(new ContactResponse(contacts_id), HttpStatus.CREATED);
  }
  
  public class ContactResponse{
	  private long id;
	  public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ContactResponse(Long long1) {this.id = long1;}
  }
}
