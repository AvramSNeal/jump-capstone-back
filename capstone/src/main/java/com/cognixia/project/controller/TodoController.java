package com.cognixia.project.controller;

import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cognixia.project.dao.TodoDAO;
import com.cognixia.project.model.Todo;
import com.cognixia.project.repository.TodoRepository;
import com.cognixia.project.resource.TodoResourceAssembler;
import com.jayway.jsonpath.PathNotFoundException;

/**
 * @author Thomas White and Ryan Trotter
 * Controllers: RESTful API controllers 
 * 
 * @RestController: Specialized version of the controller
 * @RequestMapping: Used to map web requests to Spring Controller methods
 * @InitBinder: Identifies methods which initialize the WebDataBinder
 * @Autowired: Allows Spring to resolve and inject collaborating beans into your bean
 * @GetMapping: Maps HTTP GET requests onto specific handler methods
 *
 */
@RestController
@RequestMapping(value = "/services", produces = "application/hal+json")
public class TodoController {

	private final TodoRepository repository;
	private final TodoResourceAssembler assembler;

	TodoController(TodoRepository repository,
			TodoResourceAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}

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
	public CollectionModel<EntityModel<Todo>> getAllTodos() {

		List<EntityModel<Todo>> todos = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return new CollectionModel<EntityModel<Todo>>(todos,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(TodoController.class).getAllTodos()).withSelfRel());		
	}
	
	// GET ALL TODOS BY USER
	@GetMapping("/todo/user/{user}")
	public CollectionModel<EntityModel<Todo>> getTodoByUser(@PathVariable String user) {

		List<EntityModel<Todo>> todos = repository.findAll().stream()
				.filter(e -> e.getUser().equals(user))
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return new CollectionModel<EntityModel<Todo>>(todos,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
						.methodOn(TodoController.class).getAllTodos()).withSelfRel());		
				
	}

	// GET TODO BY ID
	@GetMapping("/todo/{id}")
	public EntityModel<Todo> getTodoById(@PathVariable Long id) throws Exception {	

		Todo todo = repository.findById(id)
				.orElseThrow(() -> new Exception());

		return assembler.toModel(todo);
	}


	// CREATE A TODO
	@PostMapping("/todo")
	public EntityModel<Todo> createTodo(@RequestBody Todo todo) throws Exception{
		Todo newTodo = todoDAO.save(todo);

		EntityModel<Todo> resource = new EntityModel<Todo>(newTodo);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(this.getClass())
						.getTodoById(todo.getId()));

		// URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		//		.buildAndExpand(newTodo.getId()).toUri();

		resource.add(linkTo.withRel("all-add"));

		return resource;


	}

	// UPDATE A TODO
	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateTodo(@RequestBody Todo todo, @PathVariable long id){
		Optional<Todo> todoOptional = todoDAO.findById(id);
		if(!todoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		todo.setId(id);
		todoDAO.save(todo);
		return ResponseEntity.noContent().build();
	}

	// DELETE TODO BY ID
	@DeleteMapping("/todos/{id}")
	public void deleteStudent(@PathVariable long id) {
		todoDAO.deleteById(id);
	}

}
