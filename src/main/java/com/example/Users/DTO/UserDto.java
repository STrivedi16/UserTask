package com.example.Users.DTO;

public class UserDto {

	private String name;

	private String email;

	private String password;

	private String address;

	private String city;

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

	public UserDto(String name, String email, String password, String address, String city) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.city = city;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
