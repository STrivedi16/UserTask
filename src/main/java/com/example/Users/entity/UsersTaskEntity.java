package com.example.Users.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SQLDelete(sql = "update users_task_entity set is_active=false where id=?")
@Where(clause = "is_active=true")
public class UsersTaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Users user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Tasks tasks;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usertask")
	@JsonIgnore
	private UserTaskRatingEntity rating;
	
	private boolean is_active=true;
	
	

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public UserTaskRatingEntity getRating() {
		return rating;
	}

	public void setRating(UserTaskRatingEntity rating) {
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Tasks getTasks() {
		return tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	public UsersTaskEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UsersTaskEntity(int id, Users user, Tasks tasks, Status status) {
		super();
		this.id = id;
		this.user = user;
		this.tasks = tasks;
		this.status = status;
	}

}
