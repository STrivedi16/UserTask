package com.example.Users.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UsersTaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Users user;

	@ManyToOne(fetch = FetchType.LAZY)
	private Tasks tasks;

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

	public UsersTaskEntity(int id, Users user, Tasks tasks) {
		super();
		this.id = id;
		this.user = user;
		this.tasks = tasks;
	}

	public UsersTaskEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
