package com.charliecwb.springbootmongodb.controllers;

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

import com.charliecwb.springbootmongodb.entities.PostEntity;
import com.charliecwb.springbootmongodb.services.PostService;
import com.charliecwb.springbootmongodb.utils.Util;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	@Autowired
	private PostService service;
		
	@GetMapping
	public ResponseEntity<List<PostEntity>> findAll() {
		List<PostEntity> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<PostEntity>> findByTitle(@RequestParam(value = "text", defaultValue = "") String param) {
		List<PostEntity> posts = service.findByTitle(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/bodysearch")
	public ResponseEntity<List<PostEntity>> findByBody(@RequestParam(value = "text", defaultValue = "") String param) {
		List<PostEntity> posts = service.findByBody(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/commentssearch")
	public ResponseEntity<List<PostEntity>> findByComments(@RequestParam(value = "text", defaultValue = "") String param) {
		List<PostEntity> posts = service.findByComments(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}		
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<PostEntity>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text, 
			@RequestParam(value = "minDate", defaultValue = "") String min, 
			@RequestParam(value = "maxDate", defaultValue = "") String max) {
		List<PostEntity> posts = service.fullSearch(Util.decodeParam(text), 
				Util.convertDate(min, new Date(0L)), Util.convertDate(max, new Date()));
		return ResponseEntity.ok().body(posts);
	}	
}
