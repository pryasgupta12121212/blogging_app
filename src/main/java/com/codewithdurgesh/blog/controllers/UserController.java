package com.codewithdurgesh.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;

@RestController
@RequestMapping("/api/users")
@Tag(name="User APIs" ,description="Create,Read,Update & Delete Users")
public class UserController {
	
	@Autowired
	private UserService userService;

	// POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
		UserDto updateUser=userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updateUser);
		
	}
	
	/*
	 * @DeleteMapping("/{userId}") public
	 * ResponseEntity<?>deleteUser(@PathVariable("userId") Integer userid ) {
	 * deleteUser(userid); return new
	 * ResponseEntity(Map.of("message","User Deleted Succesfully"),HttpStatus.OK); }
	 */

	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer userid )
	{
		userService.deleteUser(userid);
		return new ResponseEntity <ApiResponse>((HttpStatusCode) new ApiResponse("User deleted successfully",true));
	}
	

	

	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllusers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	
	
	
	
	
	
	
}
