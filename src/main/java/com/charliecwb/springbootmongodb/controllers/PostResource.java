package com.charliecwb.springbootmongodb.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.charliecwb.springbootmongodb.entities.PostEntity;
import com.charliecwb.springbootmongodb.services.PostService;
import com.charliecwb.springbootmongodb.utils.Util;

@Controller
public class PostResource {
	@Autowired
	private PostService service;
		
	@GetMapping(value = "/posts/myposts")
	public String findMyPosts(Authentication auth, Model model) {
		List<PostEntity> list = service.findByAuthor(auth.getName());
        model.addAttribute("posts", list);
        return "posts";
	}
	
	@DeleteMapping(value = "/posts/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/posts/titlesearch")
	public ResponseEntity<List<PostEntity>> findByTitle(@RequestParam(value = "text", defaultValue = "") String param) {
		List<PostEntity> posts = service.findByTitle(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/posts/bodysearch")
	public ResponseEntity<List<PostEntity>> findByBody(@RequestParam(value = "text", defaultValue = "") String param) {
		List<PostEntity> posts = service.findByBody(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/posts/commentssearch")
	public ResponseEntity<List<PostEntity>> findByComments(@RequestParam(value = "text", defaultValue = "") String param) {
		List<PostEntity> posts = service.findByComments(Util.decodeParam(param));
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/posts/fullsearch")
	public ResponseEntity<List<PostEntity>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text, 
			@RequestParam(value = "minDate", defaultValue = "") String min, 
			@RequestParam(value = "maxDate", defaultValue = "") String max) {
		List<PostEntity> posts = service.fullSearch(Util.decodeParam(text), 
				Util.convertDate(min, new Date(0L)), Util.convertDate(max, new Date()));
		return ResponseEntity.ok().body(posts);
	}	
}
