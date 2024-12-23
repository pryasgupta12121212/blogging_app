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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="categories")
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Integer categoryId;
	

	private String categoryTitle;
	
	@Column(name = "description")
	private String categoryDescription;
	
	       //if remove parent then child also gets remove cascadetype.All means if you want parent nikle aur child nhi nikle then you can use fetch=FetchType.LAZY
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();
	
	
	

}