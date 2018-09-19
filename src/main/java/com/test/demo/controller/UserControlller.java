package com.test.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.demo.model.User;
import com.test.demo.service.UserRepository;

@RestController
public class UserControlller {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public User retrieveAllUsers() {
		System.out.println("User get api-------------------------");
		return new User();
//		return userRepository.findAll();
	}

	@PostMapping("/users")
	public User createStudent(@RequestBody User user) {
		User u1 = userRepository.save(user);
		System.out.println("User post api-------------------------"+u1);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(u1.getId()).toUri();
		return user;
//		return ResponseEntity.created(location).build();

	}
}
