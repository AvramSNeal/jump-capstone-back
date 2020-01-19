package com.cognixia.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cognixia.project.model.User;

@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findUserByName(String username);
	
	User findById(long id);
	

}
