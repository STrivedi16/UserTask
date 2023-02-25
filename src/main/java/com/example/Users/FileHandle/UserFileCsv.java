package com.example.Users.FileHandle;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserFileCsv {

	@Id
	private int id;
	
	private String name;
	
	private String city;
	
	private String state;

	public UserFileCsv(int id, String name, String city, String state) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
	}

	public UserFileCsv() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "UserFileCsv [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + "]";
	}
	
	
}
