
 package com.codewithdurgesh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.Security.CustomUserDetailService;
import com.codewithdurgesh.blog.Security.JwtTokenHelper;
import com.codewithdurgesh.blog.payloads.JwtAuthRequest;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
  
  @RequestMapping("/api/v1/auth")
@Tag(name="Auth Apis" ,description="Create,Read,Update & Delete Apis")
 public class AuthController {
  
	@Autowired
	private AuthenticationManager authenticationManager;
	
  
  @Autowired 
 private CustomUserDetailService customuserdetailsservice;
  
  @Autowired
  private JwtTokenHelper  JwtTokenHelper;
  
  @Autowired
  private UserService userservice;
  
  
  
  @PostMapping("/login")
  public ResponseEntity<?> createToken(@RequestBody JwtAuthRequest request) throws Exception {
   
	  this.authenticate(request.getUsername(), request.getPassword());
	  
	  
	  
      // Load user details using the email
	  UserDetails userDetails = this.customuserdetailsservice.loadUserByUsername(request.getUsername());
      // Here, you would generate a token using the userDetails (not shown)
    
		String token = JwtTokenHelper.generateToken(userDetails);

      return new ResponseEntity<>(token,HttpStatus.OK); // Assuming you have a JwtResponse class
  }
  
  
  
  
  @GetMapping("/")
  public ResponseEntity<?> getDetails(HttpServletRequest request)
  {
	  return new ResponseEntity<>("Hello,Welcome to Becoder",HttpStatus.OK);
  }
  
  
  
  
  
  
  
  
  
  
  private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new Exception("Invalid username or password !!");
		}

	}
  
  
  
  @PostMapping("/register")
  public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
  {
	  UserDto registeredUser=userservice.registerNewUser(userDto);
	  
	  return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
  }
  
  
  
}
  


	