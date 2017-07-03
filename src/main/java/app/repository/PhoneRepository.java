package app.repository;

import org.springframework.data.repository.CrudRepository;

import app.model.Contact;
import app.model.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
//	public void customDelete(Long id);
}
