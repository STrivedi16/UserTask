package com.example.Users.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Solution {

	@Id
	private int id;

	private String solution;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private QueryPortal portal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public QueryPortal getPortal() {
		return portal;
	}

	public void setPortal(QueryPortal portal) {
		this.portal = portal;
	}

	public Solution(int id, String solution, QueryPortal portal) {
		super();
		this.id = id;
		this.solution = solution;
		this.portal = portal;
	}

	public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}

}
