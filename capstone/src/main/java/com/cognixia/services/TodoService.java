package com.cognixia.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cognixia.dao.TodoDAO;
import com.cognixia.project.model.Todo;
import com.cognixia.project.repository.TodoRepository;

@Service
@Primary
public class TodoService {

	@Autowired
	TodoDAO todoDAO;

	// CREATE
	public Todo addTodo(Todo todo) throws SQLException {
		return todoDAO.addTodo(todo);
	}

	// READ ALL
	public List<Todo> listAllTodos() throws SQLException {

		List<Todo> todos = null;
		todos = todoDAO.listAllTodos();
		return todos;
	}

	// READ BY ONE TODO BY ID
	public Todo getTodoById(Long todoId) throws SQLException {

		Todo todo = todoDAO.getTodoById(todoId);	
		return todo;
	}

	// UPDATE
	public void updateTodo(Todo todo) throws SQLException {
		todoDAO.updateTodo(todo);
	}

	// DELETE
	public void deleteTodo(Long todoId) throws SQLException {	
		todoDAO.deleteTodo(todoId);
	}	
}
