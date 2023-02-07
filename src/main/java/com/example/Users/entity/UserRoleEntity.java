package com.example.Users.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Users users;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Roles roles;

	@CreationTimestamp
	private Timestamp creationTime;

	@UpdateTimestamp
	private Timestamp updationtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getUpdationtime() {
		return updationtime;
	}

	public void setUpdationtime(Timestamp updationtime) {
		this.updationtime = updationtime;
	}

	public UserRoleEntity(int id, Users users, Roles roles, Timestamp creationTime, Timestamp updationtime) {
		super();
		this.id = id;
		this.users = users;
		this.roles = roles;
		this.creationTime = creationTime;
		this.updationtime = updationtime;
	}

	public UserRoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
