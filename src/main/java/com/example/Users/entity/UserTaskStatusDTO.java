package com.example.Users.entity;

public class UserTaskStatusDTO {

	private Integer userid;

	private Integer taskid;

	private Status status;

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

	public UserTaskStatusDTO(Integer userid, Integer taskid, Status status) {
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

	public UserTaskStatusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
