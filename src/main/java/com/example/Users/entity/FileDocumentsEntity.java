package com.example.Users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileDocumentsEntity {

	@Id
	private int id;
	
	private String name;
	
	@Column(length = 1000)
	private String comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public FileDocumentsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileDocumentsEntity(int id, String name, String comments) {
		super();
		this.id = id;
		this.name = name;
		this.comments = comments;
	}
	
	
}
