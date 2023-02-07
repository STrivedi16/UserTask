package com.example.Users.Responce;

public class ResourceNotFoundException extends RuntimeException {

	private String resouceName;

	private String fieldName;

	private long fieldvalue;

	public String getResouceName() {
		return resouceName;
	}

	public void setResouceName(String resouceName) {
		this.resouceName = resouceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getFieldvalue() {
		return fieldvalue;
	}

	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

	public ResourceNotFoundException(String resouceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with %s :%s", resouceName, fieldName, fieldvalue));
		this.resouceName = resouceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

}
