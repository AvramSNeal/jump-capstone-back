package com.cognixia.project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.project.model.Todo;
import com.cognixia.project.repository.TodoRepository;

@Service
public class TodoDAO {
	
	@Autowired
	TodoRepository repository;
	
	// SAVE TODO 
	public Todo save(Todo todo) {
		return repository.save(todo);
	}
	
	// FIND ALL TODOS
	public List<Todo> findAll() {
		return repository.findAll();
	}
	
	// FIND TODO BY ID
	public Todo findById(Long id) {
		return repository.findById(id).get();
	}
	
	// DELETE TODO
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
