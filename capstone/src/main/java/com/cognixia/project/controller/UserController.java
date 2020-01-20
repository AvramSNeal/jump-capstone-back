package com.cognixia.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.project.dao.UserDAO;
import com.cognixia.project.model.User;

@RestController
@RequestMapping("/test")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@GetMapping("/users")
	public List<User> getAllTodos() {
		return userDAO.findAll();
	}

}
