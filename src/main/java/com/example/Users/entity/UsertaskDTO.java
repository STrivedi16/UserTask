package com.example.Users.entity;

public class UsertaskDTO {

	private Integer userid;

	private Integer taskid;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getTaskid() {
		return taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public UsertaskDTO(Integer userid, Integer taskid) {
		super();
		this.userid = userid;
		this.taskid = taskid;
	}

	public UsertaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
