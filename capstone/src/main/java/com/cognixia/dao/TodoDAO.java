package com.cognixia.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognixia.project.model.Todo;
import com.cognixia.project.repository.TodoRepository;

@Component
public class TodoDAO {
	
	@Autowired
	TodoRepository repository;

	// CREATE
	public Todo addTodo(Todo todo) throws SQLException {
		return repository.save(todo);
	}

	// READ
	public List<Todo> listAllTodos() throws SQLException {
		List<Todo> todos = null;
		todos = repository.findAll();
		return todos;
	}

	// READ ONE TODO BY ID
	public Todo getTodoById(long todoId) throws SQLException {

		Todo todo = repository.findById(todoId);	
		return todo;
	}

	// UPDATE
	public void updateTodo(Todo todo) throws SQLException {
		repository.save(todo);
	}

	// DELETE
	public void deleteTodo(long todoId) throws SQLException {	
		repository.deleteById(todoId);
	}	

}
