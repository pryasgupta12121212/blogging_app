package com.codewithdurgesh.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.entities.Role;
import com.codewithdurgesh.blog.repositories.RoleRepo;

@SpringBootApplication

public class BlogApplication implements CommandLineRunner {

	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private RoleRepo rolerepo;
	 

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	 * System.out.println(passwordEncoder.encode("root"));
	 * 
	 * 
	 * String abc=passwordEncoder.encode("xyz"); boolean isMatch =
	 * passwordEncoder.matches("root",abc ); System.out.print(isMatch);
	 * 
	 * 
	 * }
	 */

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			
			List<Role> roles=List.of(role,role1);
			List<Role> result=rolerepo.saveAll(roles);
			
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
