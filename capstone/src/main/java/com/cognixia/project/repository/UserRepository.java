package com.cognixia.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cognixia.project.model.User;

@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT user_username FROM users WHERE user_username = ?1", nativeQuery = true)
	public User getUserByUsername(String username);
}
