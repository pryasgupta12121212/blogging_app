package com.codewithdurgesh.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;

	@ManyToOne
	private Post post;

	// Default constructor
	public Comment() {
	}

	// Getter for id
	public int getId() {
		return id;
	}

	// Setter for id
	public void setId(int id) {
		this.id = id;
	}

	// Getter for content
	public String getContent() {
		return content;
	}

	// Setter for content
	public void setContent(String content) {
		this.content = content;
	}

	// Getter for post
	public Post getPost() {
		return post;
	}

	// Setter for post
	public void setPost(Post post) {
		this.post = post;
	}
}
