package com.charliecwb.springbootmongodb.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charliecwb.springbootmongodb.dto.UserDetailDTO;
import com.charliecwb.springbootmongodb.services.UserService;

@RestController
@RequestMapping(value="/login")
public class LoginResource {
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/validate")
	public ResponseEntity<Void> findByUserName(@RequestBody UserDetailDTO obj) {
		service.findByUserName(obj.getUserName());
		return ResponseEntity.ok().build();
	}
}
