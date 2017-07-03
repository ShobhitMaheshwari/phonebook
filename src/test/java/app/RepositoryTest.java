package app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import app.model.Contact;
import app.model.Phone;
import app.model.User;
import app.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {app.Application.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations="classpath:test.properties")
public class RepositoryTest {
	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder encoder;
	private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);	
	@Test
	public void TestRepo() {
		User user = new User("shobhit", encoder.encode("shobhit"), "shobhit", null, "USER");
		
		Contact contact = new Contact("Jolly", "Maheshwari", user);
		user.addContact(contact);
		
		Phone phone = new Phone("+18313462916");
		contact.addPhones(phone);
		userRepository.save(user);
	}
}