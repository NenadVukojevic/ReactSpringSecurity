package birch.blue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birch.blue.config.JWTTokenHelper;
import birch.blue.model.AuthenticationRequest;
import birch.blue.model.AuthenticationResponse;
import birch.blue.services.CustomUserService;

@CrossOrigin("*") // (origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomUserService customUserService;
	
	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest credentials) throws Exception {
		System.out.println("Something else: " + credentials.getUsername());
		try {
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		UserDetails userDetails = customUserService.loadUserByUsername(credentials.getUsername());
		
		String jwt = jWTTokenHelper.generateToken(userDetails);
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setJwt(jwt);
		
		return ResponseEntity.ok(response);
	}
}
