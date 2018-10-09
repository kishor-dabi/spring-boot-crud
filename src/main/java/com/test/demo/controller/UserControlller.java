package com.test.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.demo.model.Account;
import com.test.demo.model.Address;
import com.test.demo.model.User;
import com.test.demo.service.UserRepository;

@RestController
public class UserControlller {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public User retrieveUsers() {
		System.out.println("User get api-------------------------");
		Address a1 = new Address();
		a1.setAddess("addess");
		a1.setState("mp");	
//		List<Account> l1 = new ArrayList<Account>();
		User u = new User(/*(long) 1,*/"abc","545589898",a1,new ArrayList<Account>());
		userRepository.save(u);
		return u;
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
	
	@GetMapping("/allusers")
	public List<User> retrieveAllUsers() {
		System.out.println("User get all api-------------------------");

		return userRepository.findAll(); 
	}
}
