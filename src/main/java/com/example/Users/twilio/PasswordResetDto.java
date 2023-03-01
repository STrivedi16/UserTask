package com.example.Users.twilio;

public class PasswordResetDto {

	public  String userNumber;
	
	public  String userName;
	
	
	

	public PasswordResetDto(String userNumber, String userName) {
		super();
		this.userNumber = userNumber;
		this.userName = userName;
	}




	public String getUserNumber() {
		return userNumber;
	}




	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public PasswordResetDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
