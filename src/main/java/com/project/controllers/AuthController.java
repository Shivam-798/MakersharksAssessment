package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.project.payloads.JwtAuthRequest;
import com.project.payloads.JwtAuthResponse;
import com.project.security.JwtHelper;
import com.project.service.UserService;

@RestController
@RequestMapping("/api/user/auth")
public class AuthController {

	@Autowired
	private JwtHelper jwtHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		System.out.println("We are AuthController "+request.getUsername());
		this.authenticate(request.getUsername(), request.getPassword());
		
		UserDetails details = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtHelper.generateToken(details);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		System.out.println("Response mein kya aya hai   " +response.getToken());
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}
	
         private void authenticate (String username,String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password); 
		   try {
			this.authenticationManager.authenticate(authenticationToken);

		}
		catch(BadCredentialsException e) {
			System.out.println("Invalid Details ");
			throw new Exception("Invalid username or Password");
		}
		
	}
}
