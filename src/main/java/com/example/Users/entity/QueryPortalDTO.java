package com.example.Users.entity;

public class QueryPortalDTO {

	private int id;

	private String query;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public QueryPortalDTO(int id, String query) {
		super();
		this.id = id;
		this.query = query;
	}

	public QueryPortalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
