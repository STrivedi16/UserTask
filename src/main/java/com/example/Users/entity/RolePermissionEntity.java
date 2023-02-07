package com.example.Users.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RolePermissionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Roles roles1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Permissions permissions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Roles getRoles1() {
		return roles1;
	}

	public void setRoles1(Roles roles1) {
		this.roles1 = roles1;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	public RolePermissionEntity(int id, Roles roles1, Permissions permissions) {
		super();
		this.id = id;
		this.roles1 = roles1;
		this.permissions = permissions;
	}

	public RolePermissionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
