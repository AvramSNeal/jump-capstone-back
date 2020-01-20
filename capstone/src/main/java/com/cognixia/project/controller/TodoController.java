package com.cognixia.project.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.project.dao.TodoDAO;
import com.cognixia.project.model.Todo;

@RestController
@RequestMapping("/services")
public class TodoController {
	
	@Autowired
	TodoDAO todoDAO;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	// GET ALL TODOS
	@GetMapping("/todo")
	public List<Todo> getAllTodos() {
		return todoDAO.findAll();
	}
	
	// GET TODO BY ID
	@GetMapping("/todo/{id}")
	public EntityModel<Todo> getTodoById(@PathVariable (value="id") Long id) throws Exception {
		
		Optional<Todo> todo = todoDAO.findById(id);
		
		if(!todo.isPresent()) {
			throw new Exception("id-" + id);
		}
		
		EntityModel<Todo> resource = new EntityModel<Todo>(todo.get());		
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getAllTodos());
		
		resource.add(linkTo.withRel("all-todos"));
		return resource;
	}
	/*
	// CREATE A TODO
	@PostMapping("/todo")
	public Todo save(@RequestBody Todo todo) {		
		return todoDAO.save(todo);		
	}
	
	// UPDATE A TODO
	@PutMapping("/todo/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable (value="id") int id, 
			@Valid @RequestBody Todo todoDetails) {
		
		Todo newTodo = todoDAO.findById(todoDetails.getId());
		
		if(newTodo == null) {
			return ResponseEntity.notFound().build();
		}
		
		newTodo.setDescription(todoDetails.getDescription());
		newTodo.setStatus(todoDetails.isStatus());
		newTodo.setTargetDate(todoDetails.getTargetDate());
		
		Todo todo = todoDAO.save(newTodo);
		return ResponseEntity.ok().body(newTodo);	
	}
	
	// DELETE TODO BY ID
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<Todo> deleteTodo(@PathVariable (value = "id") Long id) {
		
		Todo todo = todoDAO.findById(id);
		
		if(todo == null) {
			return ResponseEntity.notFound().build();
		}
		
		todoDAO.deleteById(todo.getId());
		
		return ResponseEntity.ok().build();
	}*/
}
