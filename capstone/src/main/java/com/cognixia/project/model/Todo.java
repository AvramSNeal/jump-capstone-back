package com.cognixia.project.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "todo_table")
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_id")
	private Long id;

	@Size(min = 5, message="The description should be at least 5 characters long.")
	@NotNull
	@Column(name = "todo_description")
	private String description;
	
	@NotNull
	@Column(name = "todo_user")
	private String user;
	
	@NotNull
	@FutureOrPresent(message="Date must be in the future or present!")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name = "todo_date")
	private LocalDate targetDate;
	
	@NotNull
	@Column(name = "todo_priority")
	private int priority;
	
	@NotNull
	@Column(name = "todo_status")
	private boolean status;
	
	protected Todo() { super(); }
	
	public Todo(Long id, String description, String user, LocalDate targetDate, boolean status) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
		this.targetDate = targetDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
}

