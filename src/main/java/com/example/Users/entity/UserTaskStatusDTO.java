package com.example.Users.entity;

public class UserTaskStatusDTO {

	private Integer userid;

	private Integer taskid;

	private Status status;

	private Integer rating;

	private String rateby;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRateby() {
		return rateby;
	}

	public void setRateby(String rateby) {
		this.rateby = rateby;
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

	public UserTaskStatusDTO(Integer userid, Integer taskid, Status status, Integer rating, String rateby) {
		super();
		this.userid = userid;
		this.taskid = taskid;
		this.status = status;
		this.rating = rating;
		this.rateby = rateby;
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
