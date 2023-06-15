package com.charliecwb.springbootmongodb.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.domain.Post;
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
	
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> findByBody(String text) {
		return repository.findByBodyContainingIgnoreCase(text);
	}
	
	public List<Post> findByComments(String text) {
		return repository.findByCommentsTextContainingIgnoreCase(text);
	}	
		
	public List<Post> fullSearch(String text, Date min, Date max) {
		max = new Date(max.getTime() + 24 * 60 * 60 * 1000); 
		return repository.fullSearch(text, min, max);
	}	
	
	public Post insert(Post obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
}
