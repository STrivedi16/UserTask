package com.example.Users.FileHandle;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UsersFileEntity {

	@Id
	private int id;

	private String name;

	private String discription;

	private long salary;

	public UsersFileEntity(int id, String name, String discription, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.discription = discription;
		this.salary = salary;
	}

	public UsersFileEntity() {
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

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

}
