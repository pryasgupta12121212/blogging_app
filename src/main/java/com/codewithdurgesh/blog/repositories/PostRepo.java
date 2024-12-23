package com.codewithdurgesh.blog.repositories;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.PostDto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface PostRepo extends JpaRepository<Post,Integer> {

	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	
	List<Post> findByTitleContaining(String title);
	
}
