package com.example.Users.entity;

public class UserRoleDTO {

	private Integer userid;

	private Integer roleid;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public UserRoleDTO(Integer userid, Integer roleid) {
		super();
		this.userid = userid;
		this.roleid = roleid;
	}

	public UserRoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
