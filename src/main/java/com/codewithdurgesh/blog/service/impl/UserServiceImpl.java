package com.codewithdurgesh.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.entities.Role;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.RoleRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.service.UserService;
import com.codewithdurgesh.blog.exception.*;



@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userrepo;
	
	
	@Autowired
	private RoleRepo rolerepo;
	
	
	  @Autowired
	  private PasswordEncoder passwordencoder;
	 
	
	
	
	@Override
	public UserDto createUser(UserDto user) {
		User user1=dtoToUser(user);
		User savedUser=userrepo.save(user1);
		
		
		
		
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
	
		User user1=userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAbout(user.getAbout());
        
        User saveduser=userrepo.save(user1);
        
        return userToDto(saveduser);
        
		
	}

	@Override
	public UserDto getUserById(Integer uderId) {
		User user1=userrepo.findById(uderId).orElseThrow(()->new ResourceNotFoundException("User","id",uderId));
	
	
	return userToDto(user1);
	
	
	}

	@Override
	public List<UserDto> getAllUsers() {
	
	List<User>users=userrepo.findAll();
		List<UserDto>userdto=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		
		
		return userdto;
	}

	/*
	 * @Override public UserDto registerNewUser(UserDto user) { User
	 * user1=modelmapper.map(user, User.class);
	 * 
	 * //encoded password
	 * user1.setPassword(passwordencoder.encode(user.getPassword()));
	 * 
	 * return null; }
	 */
	/*
	 * @Override public void deleteUser(Integer userId) { User
	 * user=userrepo.findById(userId).orElseThrow(()->new
	 * ResourceNotFoundException("User","id",userId)); userrepo.delete(user);
	 * 
	 * }
	 */
	
	
	public User dtoToUser(UserDto userDto)
	{
		User user=modelmapper.map(userDto,User.class);
		
		return user;
		/*user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		*/
		
	}
	
	
	public UserDto userToDto(User user)
	{
		UserDto userDto=modelmapper.map(user,UserDto.class);
		
		/*
		 * userDto.setId(user.getId()); userDto.setEmail(user.getEmail());
		 * userDto.setName(user.getName()); userDto.setPassword(user.getPassword());
		 * userDto.setAbout(user.getAbout());
		 */
		return userDto;
		
		
		
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user=modelmapper.map(userDto,User.class);
		user.setPassword(passwordencoder.encode(user.getPassword()));
		Role role=rolerepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		
		User newUser=userrepo.save(user);
		
		return this.modelmapper.map(newUser,UserDto.class);
		
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}

}
