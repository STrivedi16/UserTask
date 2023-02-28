package com.example.Users.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SQLDelete(sql = "update roles set is_active=false where id=?")
@Where(clause = "is_active=true")
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

	
	private boolean is_active=true;
	
	
	
	
	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

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

	

	public RolePermissionEntity(int id, Roles roles1, Permissions permissions, boolean is_active) {
		super();
		this.id = id;
		this.roles1 = roles1;
		this.permissions = permissions;
		this.is_active = is_active;
	}

	public RolePermissionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
