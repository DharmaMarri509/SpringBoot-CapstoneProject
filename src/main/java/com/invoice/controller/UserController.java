package com.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.entity.User;
import com.invoice.exception.BlankNameException;
import com.invoice.exception.UserAlreadyExistException;
import com.invoice.exception.UserNotFoundException;
import com.invoice.service.UserService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")

public class UserController {
	
	
	
	private UserService service;
	
	 public UserController(UserService service){
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws BlankNameException, UserAlreadyExistException{
		
			User savedUser = service.saveUser(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Integer> loginUser(@RequestParam("userName") String userName, @RequestParam("password") String password) throws UserNotFoundException{
		
		return new ResponseEntity<>(service.getUser(userName, password), HttpStatus.OK);
	}
	

	
}
