package com.example.Users.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Permissions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String permissions;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permissions")
	@JsonIgnore
	private List<RolePermissionEntity> role;

	@CreationTimestamp
	private Timestamp creationtime;

	@UpdateTimestamp
	private Timestamp updationtime;

	private boolean is_active = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public List<RolePermissionEntity> getRole() {
		return role;
	}

	public void setRole(List<RolePermissionEntity> role) {
		this.role = role;
	}

	public Timestamp getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Timestamp creationtime) {
		this.creationtime = creationtime;
	}

	public Timestamp getUpdationtime() {
		return updationtime;
	}

	public void setUpdationtime(Timestamp updationtime) {
		this.updationtime = updationtime;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Permissions(int id, String permissions, List<RolePermissionEntity> role, Timestamp creationtime,
			Timestamp updationtime, boolean is_active) {
		super();
		this.id = id;
		this.permissions = permissions;
		this.role = role;
		this.creationtime = creationtime;
		this.updationtime = updationtime;
		this.is_active = is_active;
	}

	public Permissions() {
		super();
		// TODO Auto-generated constructor stub
	}

}
