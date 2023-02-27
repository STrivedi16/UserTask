package com.example.Users.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TempUserDto {

	@Id
	private int id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private String city;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public TempUserDto(int id, String name, String email, String password, String address, String city) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.city = city;
	}

	public TempUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
