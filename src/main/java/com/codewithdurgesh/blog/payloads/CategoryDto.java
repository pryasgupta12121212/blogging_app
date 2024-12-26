package com.codewithdurgesh.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {

	private Integer categoryId;

	@NotBlank
	@Size(min = 4, message = "Min size of category title is 4")
	private String categoryTitle;

	@NotBlank
	@Size(max = 10, message = "Max size of category description is 10")
	private String categoryDescription;

	// Default constructor
	public CategoryDto() {
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
}
