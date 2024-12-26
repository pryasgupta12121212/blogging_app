package com.codewithdurgesh.blog.payloads;

public class CommentDto {

	private int id;
	private String content;

	// Default constructor
	public CommentDto() {
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
}
