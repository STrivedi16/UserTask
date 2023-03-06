package com.example.Users.entity;

public class UsertaskDto {

	private Integer userid;

	private Integer taskid;

	private Status status;

	public UsertaskDto(Integer userid, Integer taskid, Status status) {
		super();
		this.userid = userid;
		this.taskid = taskid;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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

	public UsertaskDto(Integer userid, Integer taskid) {
		super();
		this.userid = userid;
		this.taskid = taskid;
	}

	public UsertaskDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
