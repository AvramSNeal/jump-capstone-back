package com.cognixia.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognixia.project.model.User;
import com.cognixia.project.repository.UserRepository;

@Component
public class UserDAO {
	
	@Autowired
	UserRepository repository;
	
	// CREATE
	public void addUser(User user) throws SQLException {
		repository.save(user);
	}

	// READ ALL
	public List<User> listAllUsers() throws SQLException {
		
		List<User> userList = new ArrayList<User>();
		userList = repository.findAll();	
		
		return userList;
	}
	
	// READ ONE BY ID
	public User getUserById(int userId) throws SQLException {
		User newUser = repository.findById(userId);
		return newUser;
	}
	
	// READ ONE BY USERNAME
	public User getUserByUsername(String username) throws SQLException {
		User newUser = repository.findUserByName(username).stream().findFirst().get();
		return newUser;
	}

	// UPDATE
	public void updateUser(User user) throws SQLException {
		repository.save(user);
	}

	// DELETE
	public void deleteUser(long userId) throws SQLException {	
		repository.deleteById(userId);
	}	
}
