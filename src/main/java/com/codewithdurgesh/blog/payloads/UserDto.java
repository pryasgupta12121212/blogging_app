package com.codewithdurgesh.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;

	@NotBlank
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;

	@Email(message = "Your email address is not valid")
	private String email;

	@NotBlank
	@Size(min = 3, max = 10, message = "Password must be between 3-10 characters")
	private String password;

	@NotBlank
	private String about;

	private Set<RoleDto> roles = new HashSet<>();

	// Default constructor
	public UserDto() {
	}

	// Getter for id
	public int getId() {
		return id;
	}

	// Setter for id
	public void setId(int id) {
		this.id = id;
	}

	// Getter for name
	public String getName() {
		return name;
	}

	// Setter for name
	public void setName(String name) {
		this.name = name;
	}

	// Getter for email
	public String getEmail() {
		return email;
	}

	// Setter for email
	public void setEmail(String email) {
		this.email = email;
	}

	// Getter for password
	public String getPassword() {
		return password;
	}

	// Setter for password
	public void setPassword(String password) {
		this.password = password;
	}

	// Getter for about
	public String getAbout() {
		return about;
	}

	// Setter for about
	public void setAbout(String about) {
		this.about = about;
	}

	// Getter for roles
	public Set<RoleDto> getRoles() {
		return roles;
	}

	// Setter for roles
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
}
