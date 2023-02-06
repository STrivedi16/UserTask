package com.example.Users.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String role;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	private List<UserRoleEntity> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UserRoleEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserRoleEntity> users) {
		this.users = users;
	}

	public Roles(int id, String role, List<UserRoleEntity> users) {
		super();
		this.id = id;
		this.role = role;
		this.users = users;
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

}
