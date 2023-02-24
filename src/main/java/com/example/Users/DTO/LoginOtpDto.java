package com.example.Users.DTO;

public class LoginOtpDto {

	String email;
	
	int otp;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public LoginOtpDto(String email, int otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

	public LoginOtpDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
