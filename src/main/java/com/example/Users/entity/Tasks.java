package com.example.Users.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update tasks set is_active=false where id=?")
@Where(clause = "is_active=true")
public class Tasks {

	@Id

	private int id;

	private String task;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tasks")
	List<UsersTaskEntity> users;

	private boolean is_active = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public List<UsersTaskEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UsersTaskEntity> users) {
		this.users = users;
	}

	public Tasks(int id, String task, List<UsersTaskEntity> users, boolean is_active) {
		super();
		this.id = id;
		this.task = task;
		this.users = users;
		this.is_active = is_active;
	}

	public Tasks() {
		super();
		// TODO Auto-generated constructor stub
	}

}
