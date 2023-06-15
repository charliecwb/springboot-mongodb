package com.charliecwb.springbootmongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charliecwb.springbootmongodb.domain.Post;
import com.charliecwb.springbootmongodb.resources.util.Util;
import com.charliecwb.springbootmongodb.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	@Autowired
	private PostService service;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String param) {
		List<Post> posts = service.findByTitle(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/titlesearch2")
	public ResponseEntity<List<Post>> searchTitle(@RequestParam(value = "text", defaultValue = "") String param) {
		List<Post> posts = service.searchTitle(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text, 
			@RequestParam(value = "minDate", defaultValue = "") String min, 
			@RequestParam(value = "maxDate", defaultValue = "") String max) {
		List<Post> posts = service.fullSearch(Util.decodeParam(text), 
				Util.convertDate(min, new Date(0L)), Util.convertDate(max, new Date()));
		return ResponseEntity.ok().body(posts);
	}	
}
