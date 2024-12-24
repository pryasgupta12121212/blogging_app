package com.codewithdurgesh.blog.controllers;


import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;

import java.util.List;
import java.io.InputStream;
import org.springframework.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.service.FileService;
import com.codewithdurgesh.blog.service.PostService;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("api")
@Tag(name="Post APIs", description="Create,Read,Update & Delete Post")

public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Value("${project.image}") // Inject property from application.properties or application.yml
    private String path;
	
	
	
	@Autowired
	private FileService fileservice;
	
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
	PostDto createPosts=postservice.createPost(postdto,userId,categoryId);
		return new ResponseEntity<PostDto>(createPosts,HttpStatus.CREATED);
		
	}
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		
		List<PostDto>posts=postservice.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		
		List<PostDto>posts=postservice.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value= "PageNumber", defaultValue=AppConstants.PAGE_NUMBER,  required = false) Integer pageNumber,
		@RequestParam(value= "PageSize", defaultValue=AppConstants.PAGE_SIZE,  required = false) Integer pageSize,
		@RequestParam(value="sortBy",defaultValue=AppConstants.sortBy,required=false) String sortBy
){
		
	PostResponse postResponse= postservice.getAllPost(pageNumber,pageSize,sortBy);
		
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		
	}
 
	@GetMapping("/posts/{postId}")
public ResponseEntity <PostDto> getPostById(@PathVariable Integer postId)
{
		PostDto allPost=postservice.getPostById(postId);
		
		return new ResponseEntity<PostDto>(allPost,HttpStatus.OK);
		
	}
	
	
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		postservice.deletePost(postId);
		return new ApiResponse("Post is successfully deleted !!",true);
		
	}
	
	
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable Integer postId)
	{
		PostDto updatePost=postservice.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle (@PathVariable("keywords") String keywords)
	{
		List<PostDto> result =postservice.searchPosts(keywords);
		
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	
	
	  @PostMapping("/post/image/upload/{postId}")
	  public ResponseEntity<PostDto>uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId ) throws IOException{ // get the post first.
	// Agar nhi mila to yhi pe exception throw kar dega. PostDto postDto =
	                    PostDto postDto=this.postservice.getPostById(postId);
	  
	 // get the fileName by which we have to save in database String fileName =
	        String fileName=fileservice.uploadImage(path, image);
	  
	  // ab isko database me save karna h.
			  postDto.setImageName(fileName); // ab save kar do update call karke PostDto
	 PostDto updatedPost = this.postservice.updatePost(postDto, postId); 
	 return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	   }
	
	  
	  
	  
		  
	  @GetMapping(value="/post/image/{imageName}" , produces=MediaType.IMAGE_JPEG_VALUE) 
	  public void downloadImage(
			  
			  @PathVariable("imageName") String imageName, HttpServletResponse response )
			  throws IOException {
			  
			  InputStream resource = this.fileservice.getResource(path, imageName);
			  response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			 StreamUtils.copy(resource, response.getOutputStream()); 
			  // kisko , kahan bhejna h. }
			 
	  }
	  
}
