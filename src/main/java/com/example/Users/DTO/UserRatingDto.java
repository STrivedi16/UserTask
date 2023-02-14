package com.example.Users.DTO;

public class UserRatingDto {

	private Integer id;

	private float rating;

	private String ratedby;

	private String messsage;

	private Integer task;

	public Integer getTask() {
		return task;
	}

	public void setTask(Integer task) {
		this.task = task;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getRatedby() {
		return ratedby;
	}

	public void setRatedby(String ratedby) {
		this.ratedby = ratedby;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public UserRatingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRatingDto(Integer id, float rating, String ratedby, String messsage, Integer task) {
		super();
		this.id = id;
		this.rating = rating;
		this.ratedby = ratedby;
		this.messsage = messsage;
		this.task = task;
	}

}
