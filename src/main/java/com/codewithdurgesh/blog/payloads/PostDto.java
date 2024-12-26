package com.codewithdurgesh.blog.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private UserDto user;
	private CategoryDto category;
	private Set<CommentDto> comments = new HashSet<>();

	// Default constructor
	public PostDto() {
	}

	// Getter for postId
	public Integer getPostId() {
		return postId;
	}

	// Setter for postId
	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	// Getter for title
	public String getTitle() {
		return title;
	}

	// Setter for title
	public void setTitle(String title) {
		this.title = title;
	}

	// Getter for content
	public String getContent() {
		return content;
	}

	// Setter for content
	public void setContent(String content) {
		this.content = content;
	}

	// Getter for imageName
	public String getImageName() {
		return imageName;
	}

	// Setter for imageName
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	// Getter for addedDate
	public Date getAddedDate() {
		return addedDate;
	}

	// Setter for addedDate
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	// Getter for user
	public UserDto getUser() {
		return user;
	}

	// Setter for user
	public void setUser(UserDto user) {
		this.user = user;
	}

	// Getter for category
	public CategoryDto getCategory() {
		return category;
	}

	// Setter for category
	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	// Getter for comments
	public Set<CommentDto> getComments() {
		return comments;
	}

	// Setter for comments
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
}
