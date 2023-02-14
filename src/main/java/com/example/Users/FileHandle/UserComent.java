package com.example.Users.FileHandle;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserComent {

	@Id
	private int id;

	private String name;

	private String coment;

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

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public UserComent(int id, String name, String coment) {
		super();
		this.id = id;
		this.name = name;
		this.coment = coment;
	}

	public UserComent() {
		super();
		// TODO Auto-generated constructor stub
	}

}
