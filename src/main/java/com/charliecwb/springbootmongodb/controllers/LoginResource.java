package com.charliecwb.springbootmongodb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charliecwb.springbootmongodb.models.LoginDTO;
import com.charliecwb.springbootmongodb.models.UserDetailDTO;
import com.charliecwb.springbootmongodb.services.LoginService;

@RestController
//@RequestMapping(value="/login")
public class LoginResource {
	@Autowired
	private LoginService loginService;	
	
	@PostMapping(value = "/validate")
	public ResponseEntity<LoginDTO> validateUserName(@RequestParam(value = "username", defaultValue = "") String username) {
		LoginDTO resp = loginService.validate(username);
		return ResponseEntity.ok().body(resp);
	}
	
	@PostMapping
	public ResponseEntity<Void> login(@RequestBody UserDetailDTO obj, @RequestParam(value = "code", defaultValue = "") String code) {
		//loginService.login(obj, code);
		return ResponseEntity.ok().build();
	}	
}
