package com.cognixia.project.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cognixia.project.model.Todo;
import com.cognixia.services.TodoService;
import com.cognixia.util.RestPreconditions;

@SessionAttributes("name")
@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping
	public List<Todo> listAllTodos() throws SQLException {
		return service.listAllTodos();
	}
	
	@GetMapping("/{id}")
	public Todo findById(@PathVariable("id") Long id) throws SQLException {
		return RestPreconditions.checkNotNull(service.getTodoById(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Todo addTodo(@RequestBody Todo resource) throws SQLException {
		
		RestPreconditions.checkNotNull(resource);
		return service.addTodo(resource);		
	}
	
	@RequestMapping(value="/listtodos", method=RequestMethod.GET)
	public String listTodos(ModelMap model) throws SQLException {
		
		String name = getLoggedInUser(model);
		
		model.put("username", name);
		model.put("todos", service.listAllTodos());
		model.addAttribute("todos", service.listAllTodos());
		
		return "listtodos";
	}
	
	@RequestMapping(value="/todo", method=RequestMethod.GET)
	public String getAddTodo(ModelMap model) {

		Todo todo = new Todo(0, "Default", getLoggedInUser(model), LocalDate.now(), false);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/todo", method=RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) throws SQLException {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		service.addTodo(todo);
		
		return "redirect:/listtodos";
	}
	
	@RequestMapping(value="/deletetodo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam Long todoId) throws SQLException {
		
		service.deleteTodo(todoId);
		return "redirect:/listtodos";
	}
	
	@RequestMapping(value="/edittodo", method=RequestMethod.GET)
	public String showEditTodo(ModelMap model, @RequestParam Long todoId) throws SQLException {
		
		Todo todo = service.getTodoById(todoId);
		model.addAttribute("todo", todo);
		
		return "todo";
	}
	
	@RequestMapping(value="/edittodo", method = RequestMethod.POST)
	public String editTodo(ModelMap model, @Valid Todo todo, BindingResult result) throws SQLException {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		service.updateTodo(todo);
		
		return "redirect:/listtodos";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/";
	}
	
	private String getLoggedInUser(ModelMap model) {
		
		return (String) model.get("name");
	}

}
