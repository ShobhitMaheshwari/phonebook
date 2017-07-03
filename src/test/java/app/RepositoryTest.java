package app;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import app.model.Contact;
import app.model.Phone;
import app.model.User;
import app.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {app.Application.class}, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(classes = {app.Application.class}, loader = AnnotationConfigWebContextLoader.class)
@TestPropertySource(locations="classpath:test.properties")
@WebAppConfiguration
public class RepositoryTest {
	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder encoder;
	private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);
	
	private MockMvc mockMvc;
	
	@Resource
	private WebApplicationContext webApplicationContext;
	
//	@InjectMocks
//	private AuthenticationController authenticationController;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build(); 
//				MockMvcBuilders.standaloneSetup(authenticationController).build();
	    User user = new User("shobhit", encoder.encode("shobhit"), "shobhit", null, "USER");
		Contact contact = new Contact("Jolly", "Maheshwari", user);
		user.addContact(contact);
		Phone phone = new Phone("+18313462916");
		contact.addPhones(phone);
		userRepository.save(user);
	}
	
	
	private String getToken() throws Exception {
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"username\":\"shobhit\",\"password\":\"shobhit\"}"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
		
		String content = result.getResponse().getContentAsString().replace("\n", "");
		Pattern r = Pattern.compile("\\{  \"token\" : \"(.*)\"\\}");
		Matcher m = r.matcher(content);
		if (m.find())
	         return m.group(1);
		return "";
	}
	
	@Test
	public void TestRepo() throws Exception {
		String token = getToken();
		if(!token.isEmpty()) {
			mockMvc.perform(
					MockMvcRequestBuilders.get("/contacts")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.header("X-Auth-Token", token))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].phones", hasSize(1)))
			.andExpect(jsonPath("$[0].phones[0]", is("+18313462916")))
			.andExpect(jsonPath("$[0].first_name", is("Jolly")))
			.andExpect(jsonPath("$[0].last_name", is("Maheshwari")));
		}
	}
}