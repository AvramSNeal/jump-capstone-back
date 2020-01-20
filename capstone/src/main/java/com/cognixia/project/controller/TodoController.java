package com.cognixia.project.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/todo")
	public List<Todo> getAllTodos() {
		return todoDAO.findAll();
	}
	
	@PostMapping("/todo")
	public Todo save(@RequestBody Todo todo) {		
		return todoDAO.save(todo);		
	}
}
