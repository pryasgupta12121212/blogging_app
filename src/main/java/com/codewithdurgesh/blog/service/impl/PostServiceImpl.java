package com.codewithdurgesh.blog.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.repositories.categoryRepo;
import com.codewithdurgesh.blog.service.PostService;
import com.codewithdurgesh.blog.exception.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private UserRepo userrepo;
	

	@Autowired
	private categoryRepo categoryrepo;
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		
		Category category=categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
		Post pst=modelmapper.map(postDto,Post.class);
		pst.setImageName("default.png");
		pst.setAddedDate(new Date(0));
		pst.setCategory(category);
		pst.setUser(user);
		
		
		
		Post newPost=postrepo.save(pst);
		 return this.modelmapper.map(newPost,PostDto.class);
	
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post=	postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
         post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		
	    post.setImageName(postDto.getImageName());
	    
	    Post updatedPost=postrepo.save(post);
		return modelmapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
	Post post=	postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));

	postrepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		
		
		 Pageable p=PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
		
		
		
		
	Page <Post> pagePos=postrepo.findAll(p);
	
	List<Post>allPosts= pagePos.getContent();
	
	
	
	
	
	List<PostDto>postDtos=allPosts.stream().map((post)->this.modelmapper.map(post,PostDto.class)).collect(Collectors.toList());
		
	
	PostResponse postResponse=new PostResponse();
	postResponse.setContent(postDtos);
	postResponse.setPageNumber(pagePos.getNumber());
	postResponse.setPageSize(pagePos.getSize());
	postResponse.setTotalElements(pagePos.getTotalElements());
	
	postResponse.setTotalPages(pagePos.getTotalPages());
	postResponse.setLastPage(pagePos.isLast());
	
	
	
	return postResponse;
	
	}

	@Override
	public PostDto getPostById(Integer postId) {
	  Post post=postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		
		
		
		return modelmapper.map(post,PostDto.class);
		
		
		
		
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		   List<Post>posts=postrepo.findByCategory(cat);
		
		   List<PostDto> postDtos=posts.stream().map((post)->this.modelmapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User cat=userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		   List<Post>posts=postrepo.findByUser(cat);
		
		List<PostDto>postDtos= posts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword)
	{
		List<Post>posts=postrepo.findByTitleContaining(keyword);
	List<PostDto>postDto=posts.stream().map((post)->modelmapper.map(post,PostDto.class)).collect(Collectors.toList());
	
	return postDto;
	}

	

}
