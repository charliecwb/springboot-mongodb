package com.charliecwb.springbootmongodb.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.entities.PostEntity;
import com.charliecwb.springbootmongodb.repositories.PostRepository;
import com.charliecwb.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	
	public List<PostEntity> findAll() {
		return repository.findAll(); 
	}
	
	public PostEntity findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<PostEntity> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<PostEntity> findByBody(String text) {
		return repository.findByBodyContainingIgnoreCase(text);
	}
	
	public List<PostEntity> findByComments(String text) {
		return repository.findByCommentsTextContainingIgnoreCase(text);
	}	
		
	public List<PostEntity> fullSearch(String text, Date min, Date max) {
		max = new Date(max.getTime() + 24 * 60 * 60 * 1000); 
		return repository.fullSearch(text, min, max);
	}	
	
	public PostEntity insert(PostEntity obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
}
