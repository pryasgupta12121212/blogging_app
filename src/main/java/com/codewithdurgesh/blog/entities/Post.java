package com.codewithdurgesh.blog.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(nullable = false)
	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	@ManyToOne
	private User user;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

	// Default constructor
	public Post() {
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
	public User getUser() {
		return user;
	}

	// Setter for user
	public void setUser(User user) {
		this.user = user;
	}

	// Getter for category
	public Category getCategory() {
		return category;
	}

	// Setter for category
	public void setCategory(Category category) {
		this.category = category;
	}

	// Getter for comments
	public Set<Comment> getComments() {
		return comments;
	}

	// Setter for comments
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
}
