package com.codewithdurgesh.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	private String categoryTitle;

	@Column(name = "description")
	private String categoryDescription;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();

	// Default constructor
	public Category() {
	}

	// Getter for categoryId
	public Integer getCategoryId() {
		return categoryId;
	}

	// Setter for categoryId
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	// Getter for categoryTitle
	public String getCategoryTitle() {
		return categoryTitle;
	}

	// Setter for categoryTitle
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	// Getter for categoryDescription
	public String getCategoryDescription() {
		return categoryDescription;
	}

	// Setter for categoryDescription
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	// Getter for posts
	public List<Post> getPosts() {
		return posts;
	}

	// Setter for posts
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
