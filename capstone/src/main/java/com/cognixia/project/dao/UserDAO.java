package com.cognixia.project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.project.model.User;
import com.cognixia.project.repository.UserRepository;

@Service
public class UserDAO {

	@Autowired
	UserRepository repository;

	// SAVE TODO 
	public User save(User user) {
		return repository.save(user);
	}

	// FIND ALL USERS
	public List<User> findAll() {
		return repository.findAll();
	}

	// FIND USER BY ID
	public User findById(Long id) {
		return repository.findById(id).get();
	}

	// FIND USER BY USERNAME
	public User findByName(String username) {
		return repository.getUserByUsername(username);
	}

	// DELETE USER
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}


