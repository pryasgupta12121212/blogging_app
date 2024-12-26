package com.codewithdurgesh.blog.payloads;

import java.util.List;

public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;

	// Default constructor
	public PostResponse() {
	}

	// Getter for content
	public List<PostDto> getContent() {
		return content;
	}

	// Setter for content
	public void setContent(List<PostDto> content) {
		this.content = content;
	}

	// Getter for pageNumber
	public int getPageNumber() {
		return pageNumber;
	}

	// Setter for pageNumber
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	// Getter for pageSize
	public int getPageSize() {
		return pageSize;
	}

	// Setter for pageSize
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// Getter for totalElements
	public long getTotalElements() {
		return totalElements;
	}

	// Setter for totalElements
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	// Getter for totalPages
	public int getTotalPages() {
		return totalPages;
	}

	// Setter for totalPages
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	// Getter for lastPage
	public boolean isLastPage() {
		return lastPage;
	}

	// Setter for lastPage
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
}
