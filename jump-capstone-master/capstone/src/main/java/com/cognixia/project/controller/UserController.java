package com.cognixia.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.project.dao.UserDAO;
import com.cognixia.project.model.User;
import com.cognixia.project.repository.UserRepository;

@RestController
@RequestMapping("/test")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}	
	

}
