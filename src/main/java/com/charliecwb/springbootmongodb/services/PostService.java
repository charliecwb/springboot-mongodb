package com.charliecwb.springbootmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.domain.Post;
import com.charliecwb.springbootmongodb.domain.User;
import com.charliecwb.springbootmongodb.dto.AuthorDTO;
import com.charliecwb.springbootmongodb.dto.PostDTO;
import com.charliecwb.springbootmongodb.repositories.PostRepository;
import com.charliecwb.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll() {
		return repository.findAll(); 
	}
	
	public Post findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public Post insert(Post obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
}
