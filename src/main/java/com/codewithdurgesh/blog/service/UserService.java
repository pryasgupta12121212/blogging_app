package com.codewithdurgesh.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.payloads.UserDto;

@Service
public interface UserService {

	
	
	
	
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer uderId);
	
	List<UserDto> getAllUsers();
	
	
	void deleteUser(Integer userId);
	

	
	
	
}
