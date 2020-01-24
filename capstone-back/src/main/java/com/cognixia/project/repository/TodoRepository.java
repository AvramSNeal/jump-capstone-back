package com.cognixia.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cognixia.project.model.Todo;

/**
 * @author Thomas White
 * 
 * Interface: Extends the JpaRepository to an interface to be accessed by the DAO layer
 * 
 * @CrossOrigin: Enables cross-origin resource sharing
 *
 */
@CrossOrigin("*")
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
