package com.example.Users.entity;

public class RolePermissionDTO {

	private Integer roleid;

	private Integer permissionid;

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}

	public RolePermissionDTO(Integer roleid, Integer permissionid) {
		super();
		this.roleid = roleid;
		this.permissionid = permissionid;
	}

	public RolePermissionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
