package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.model.User;
import app.repository.UserRepository;
import app.request.RegisterRequest;

@RestController
@RequestMapping("${cerberus.route.register}")
public class RegisterController {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserRepository repository;
	
	@RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<?> authenticationRequest(@RequestBody RegisterRequest user){
		User user2 = new User(user.getUsername(), encoder.encode(user.getPassword()), user.getUsername(), null, "USER");
		repository.save(user2);
		return new ResponseEntity(HttpStatus.CREATED);
		//return ResponseEntity.ok("");
	  }
}
