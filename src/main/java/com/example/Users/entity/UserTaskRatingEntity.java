package com.example.Users.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserTaskRatingEntity {

	@Id

	private int id;

	private String message;

	private float rating;

	private String ratedby;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private UsersTaskEntity usertask;

	@CreationTimestamp
	private Timestamp creationtime;

	public Timestamp getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Timestamp creationtime) {
		this.creationtime = creationtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public UsersTaskEntity getUsertask() {
		return usertask;
	}

	public void setUsertask(UsersTaskEntity usertask) {
		this.usertask = usertask;
	}

	public UserTaskRatingEntity(int id, String message, float rating, String ratedby, UsersTaskEntity usertask,
			Timestamp creationtime) {
		super();
		this.id = id;
		this.message = message;
		this.rating = rating;
		this.ratedby = ratedby;
		this.usertask = usertask;
		this.creationtime = creationtime;
	}

	public UserTaskRatingEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
