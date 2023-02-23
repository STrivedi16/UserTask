package com.example.Users.DTO;

import com.example.Users.entity.Status;

public class UsertaskDto {

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
