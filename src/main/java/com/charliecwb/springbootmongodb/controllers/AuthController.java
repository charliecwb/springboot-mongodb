package com.charliecwb.springbootmongodb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.charliecwb.springbootmongodb.models.UserDTO;
import com.charliecwb.springbootmongodb.services.PostService;
import com.charliecwb.springbootmongodb.services.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;	
	
	@GetMapping("/index")
    public String home(){
        return "index";
    }

	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }
	
	@PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDto,
                               BindingResult result,
                               Model model){
        var existingUser = userService.findByUserName(userDto.getLogin().getUserName());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
	
    @GetMapping("/posts")
    public String posts(Model model){
        var posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }    
}
