package com.example.Users.DTO;

public class QueryPortalDto {

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

	public QueryPortalDto(int id, String query) {
		super();
		this.id = id;
		this.query = query;
	}

	public QueryPortalDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
