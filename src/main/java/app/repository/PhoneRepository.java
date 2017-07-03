package app.repository;

import org.springframework.data.repository.CrudRepository;

import app.model.Contact;

public interface PhoneRepository extends CrudRepository<Contact, Long> {
	public void customDelete(Long id);
}
