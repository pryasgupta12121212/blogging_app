package com.codewithdurgesh.blog.service;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {

	//create
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	
	//delete
	void deletePost(Integer postId);
	
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	
	
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	
	List<PostDto> getPostsByUser(Integer userId);

	//search posts
	List<PostDto>searchPosts(String keyword);
	
	   
	
	
}
