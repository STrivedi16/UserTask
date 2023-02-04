package com.example.Users.Responce;

public class ErrorMessage {

	private String errormessage;

	private String errorkey;

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getErrorkey() {
		return errorkey;
	}

	public void setErrorkey(String errorkey) {
		this.errorkey = errorkey;
	}

	public ErrorMessage(String errormessage, String errorkey) {
		super();
		this.errormessage = errormessage;
		this.errorkey = errorkey;
	}

	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

}
