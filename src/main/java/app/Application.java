package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.model.User;
import app.repository.UserRepository;

@SpringBootApplication
@PropertySource(value={"classpath:application-local.properties"})
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
//  @Bean
//	public CommandLineRunner demo(UserRepository repository, PasswordEncoder encoder) {
//		return (args) -> {
//			// save a couple of customers
//			repository.save(new User("user", encoder.encode("password"), "user", null, "USER"));
//			repository.save(new User("admin", encoder.encode("admin"), "admin", null, "ADMIN, ROOT"));			
//		};
//	}
}
