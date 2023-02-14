package com.example.Users.DTO;

public class UserRoleDto {

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

	public UserRoleDto(Integer userid, Integer roleid) {
		super();
		this.userid = userid;
		this.roleid = roleid;
	}

	public UserRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
