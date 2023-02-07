package com.example.Users.Responce;

public class ApiResponce {

	private String message;

	private Boolean success;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiResponce(String message, Boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public ApiResponce() {
		super();
		// TODO Auto-generated constructor stub
	}

}
