package com.example.Users.Responce;

public class Success {

	private String successMsg;

	private String Successkey;

	private Object object;

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getSuccesskey() {
		return Successkey;
	}

	public void setSuccesskey(String successkey) {
		Successkey = successkey;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Success(String successMsg, String successkey, Object object) {
		super();
		this.successMsg = successMsg;
		this.Successkey = successkey;
		this.object = object;
	}

	public Success() {
		super();
		// TODO Auto-generated constructor stub
	}

}
