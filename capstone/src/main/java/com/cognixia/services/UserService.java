package com.cognixia.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cognixia.dao.UserDAO;
import com.cognixia.project.model.User;
import com.cognixia.project.repository.UserRepository;

public class UserService implements UserDetailsService {
	
	@Autowired
	UserDAO userDAO;
	
	// CREATE
	public void addUser(User user) throws SQLException {
		userDAO.addUser(user);
	}

	// READ ALL
	public List<User> listAllUsers() {
		List<User> userList = new ArrayList<User>();

		try {
			userList = userDAO.listAllUsers();
			return userList;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return userList;
	}
	
	// READ ONE BY ID
	public User getUserById(int userId) throws SQLException {
		User newUser = userDAO.getUserById(userId);
		return newUser;
	}
	
	// READ ONE BY USERNAME
	public User getUserByUsername(String username) throws SQLException {
		User newUser = userDAO.getUserByUsername(username);
		return newUser;
	}

	// UPDATE
	public void updateUser(User user) throws SQLException {
		userDAO.updateUser(user);
	}

	// DELETE
	public void deleteUser(int userId) throws SQLException {	
		userDAO.deleteUser(userId);		
	}	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			User user = getUserByUsername(username);
			UserBuilder userBuilder = null;
			UserDetails userDetails = null;

			if(user != null) {
				userBuilder = org.springframework
						.security.core.userdetails.User.withUsername(user.getUsername());
				userBuilder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
				userBuilder.roles("ROLE_USER");

				userDetails = userBuilder.build();

				return userDetails;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User not found!");
		}

		return null;
	}
}
