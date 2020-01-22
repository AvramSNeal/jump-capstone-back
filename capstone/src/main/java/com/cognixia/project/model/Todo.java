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
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Thomas White
 * 
 * Model: Todo model that sets the mapping up for the spring data jpa
 * 
 * @Entity: A lightweight persistence domain object annotation
 * @Table: Allows you to specify the details of the table that will be used to persist the entity in the database
 * @Id: Specifies the primary key of an entity
 * @GeneratedValue: Provides for the specification of generation strategies for the values of primary keys
 * @Column: Used to specify the mapped column for a persistent property or field
 * @NotNull: Let you check nullability of a variable
 * @Size: Makes the bean independent of JPA and its vendors such as Hibernate while checking constraints
 * @FutureOrPresent: The value of the field or property must be a date or time in present or future.
 * @DateTimeFormat:Declares that a field or method parameter should be formatted as a date or time.
 * 
 */
@Entity
@Table(name = "todo_table")
public class Todo extends RepresentationModel<Todo> implements Serializable {

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
	
	public Todo(Long id, String description, String user, LocalDate targetDate, int priority, boolean status) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
		this.targetDate = targetDate;
		this.priority = priority;
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
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", user=" + user + ", targetDate=" + targetDate
				+ ", priority=" + priority + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + priority;
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((targetDate == null) ? 0 : targetDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priority != other.priority)
			return false;
		if (status != other.status)
			return false;
		if (targetDate == null) {
			if (other.targetDate != null)
				return false;
		} else if (!targetDate.equals(other.targetDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}

