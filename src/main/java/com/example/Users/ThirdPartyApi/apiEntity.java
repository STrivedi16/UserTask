package com.example.Users.ThirdPartyApi;

import java.util.Date;

public class apiEntity {

	private float lat;		//Latitude
	private float lon;		//Longitude
	
	private Date date;
	
	private float dim;
	
	private String api_key;

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getDim() {
		return dim;
	}

	public void setDim(float dim) {
		this.dim = dim;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public apiEntity(float lat, float lon, Date date, float dim, String api_key) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.date = date;
		this.dim = dim;
		this.api_key = api_key;
	}

	public apiEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
