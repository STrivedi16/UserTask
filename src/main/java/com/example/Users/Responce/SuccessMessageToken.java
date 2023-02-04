package com.example.Users.Responce;

public class SuccessMessageToken {

	String SuccessMessage;

	String SuccessKey;

	String token;

	String reftoken;

	public String getSuccessMessage() {
		return SuccessMessage;
	}

	public void setSuccessMessage(String successMessage) {
		SuccessMessage = successMessage;
	}

	public String getSuccessKey() {
		return SuccessKey;
	}

	public void setSuccessKey(String successKey) {
		SuccessKey = successKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReftoken() {
		return reftoken;
	}

	public void setReftoken(String reftoken) {
		this.reftoken = reftoken;
	}

	public SuccessMessageToken(String successMessage, String successKey, String token, String reftoken) {
		super();
		this.SuccessMessage = successMessage;
		this.SuccessKey = successKey;
		this.token = token;
		this.reftoken = reftoken;
	}

	public SuccessMessageToken() {
		super();
		// TODO Auto-generated constructor stub
	}

}
