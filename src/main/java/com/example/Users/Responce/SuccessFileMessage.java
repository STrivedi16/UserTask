package com.example.Users.Responce;

public class SuccessFileMessage {

	String successMessage;
	String successKey;

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getSuccessKey() {
		return successKey;
	}

	public void setSuccessKey(String successKey) {
		this.successKey = successKey;
	}

	public SuccessFileMessage(String successMessage, String successKey) {
		super();
		this.successMessage = successMessage;
		this.successKey = successKey;
	}

	public SuccessFileMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

}
