package com.example.Users.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class QueryPortal {

	@Id
	private int id;

	private String query;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "portal")
	private Solution solution;

	@ManyToOne(fetch = FetchType.LAZY)
	private Users users1;

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

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public QueryPortal(int id, String query, Solution solution, Users users1) {
		super();
		this.id = id;
		this.query = query;
		this.solution = solution;
		this.users1 = users1;
	}

	public Users getUsers1() {
		return users1;
	}

	public void setUsers1(Users users1) {
		this.users1 = users1;
	}

	public QueryPortal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
