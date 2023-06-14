package com.charliecwb.springbootmongodb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.charliecwb.springbootmongodb.domain.Post;
import com.charliecwb.springbootmongodb.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	private List<PostDTO> posts = new ArrayList<>();
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		posts = user.getPosts().stream().map(x -> new PostDTO(x)).toList(); 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}	
}
