package com.cognixia.jump.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@RequestMapping("/api")
@RestController
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	@GetMapping("/hello")
//	public String hello() {
//		return "Hello World";
//	}
//	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		
		try {
			
			authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
					);
			
		} catch (Exception e) {
			
			throw new Exception("Incorrect username or password", e);
			
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		
		final String JWT = jwtUtil.generateTokens(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(JWT));
		
	}
	
	//Create new User
	@PostMapping("/registerStudent")
	public ResponseEntity<?> createNewUser(@RequestBody AuthenticationRequest newUser) throws Exception {
		
		Optional<User> isAlreadyRegistered = userRepo.findByUsername(newUser.getUsername());
		
		if (isAlreadyRegistered.isPresent()) {
			throw new Exception("User already exits");
		}
		
		String submittedPassword = newUser.getPassword();
		
		String encodedPassword = passwordEncoder.encode(submittedPassword);
		
		User registerUser = new User();
		registerUser.setUsername(newUser.getUsername());
		registerUser.setPassword(encodedPassword);
		registerUser.setEnabled(true);
		registerUser.setRole(Role.valueOf("ROLE_STUDENT"));
		
		User toSave = userRepo.save(registerUser);
		
		return ResponseEntity.ok(newUser.getUsername() 
				+ " with login ID: "+ toSave.getId() + " (IMPORTANT!NOTE IT DOWN PLEASE) created.");
	}
	
	@PostMapping("/registerInstructor")
	public ResponseEntity<?> createNewInstructor(@RequestBody AuthenticationRequest newUser) throws Exception {
		
		Optional<User> isAlreadyRegistered = userRepo.findByUsername(newUser.getUsername());
		
		if (isAlreadyRegistered.isPresent()) {
			throw new Exception("User already exits");
		}
		
		String submittedPassword = newUser.getPassword();
		
		String encodedPassword = passwordEncoder.encode(submittedPassword);
		
		User registerUser = new User();
		registerUser.setUsername(newUser.getUsername());
		registerUser.setPassword(encodedPassword);
		registerUser.setEnabled(true);
		registerUser.setRole(Role.valueOf("ROLE_INSTRUCTOR"));
		
		User toSave = userRepo.save(registerUser);
		
		return ResponseEntity.ok(newUser.getUsername() 
				+ " with login ID: "+ toSave.getId() + " created.");
	}
	@PostMapping("/registerAdmin")
	public ResponseEntity<?> createNewAdmin(@RequestBody AuthenticationRequest newUser) throws Exception {
		
		Optional<User> isAlreadyRegistered = userRepo.findByUsername(newUser.getUsername());
		
		if (isAlreadyRegistered.isPresent()) {
			throw new Exception("User already exits");
		}
		
		String submittedPassword = newUser.getPassword();
		
		String encodedPassword = passwordEncoder.encode(submittedPassword);
		
		User registerUser = new User();
		registerUser.setUsername(newUser.getUsername());
		registerUser.setPassword(encodedPassword);
		registerUser.setEnabled(true);
		registerUser.setRole(Role.valueOf("ROLE_ADMIN"));
		
		User toSave = userRepo.save(registerUser);
		
		return ResponseEntity.ok(newUser.getUsername() 
				+ " with login ID: "+ toSave.getId() + " created.");
	}
}
