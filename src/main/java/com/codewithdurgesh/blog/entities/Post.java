package com.codewithdurgesh.blog.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.codewithdurgesh.blog.payloads.CategoryDto;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.codewithdurgesh.blog.entities.User;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(nullable=false)
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	
	private User user;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	
	
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	
	
}
