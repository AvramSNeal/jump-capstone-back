package com.cognixia.project.resource;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.cognixia.project.controller.TodoController;
import com.cognixia.project.model.Todo;

import lombok.Getter;


/**
 * @author Thomas White
 * 
 * Resource: Creates a helper class for adding links to Todo objects
 * 
 * @Component: Used to denote a class as Component
 *
 */

@Component
public class TodoResourceAssembler implements RepresentationModelAssembler<Todo, EntityModel<Todo>> {

	@Override
	public EntityModel<Todo> toModel(Todo todo) {

		try {
			return new EntityModel<>(todo,
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
							.methodOn(TodoController.class)
							.getTodoById(todo.getId())).withSelfRel(),
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
							.methodOn(TodoController.class)
							.getAllTodos()).withRel("todos"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
