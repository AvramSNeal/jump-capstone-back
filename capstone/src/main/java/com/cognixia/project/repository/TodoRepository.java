package com.cognixia.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cognixia.project.model.Todo;

@CrossOrigin("*")
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
