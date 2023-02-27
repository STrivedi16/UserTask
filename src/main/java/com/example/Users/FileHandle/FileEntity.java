package com.example.Users.FileHandle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity

public class FileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String type;

	@Lob // it specifies that the database should stored the property as large object
	// is it used when if you want to store any binary format in DB;
	@Column(length = 1000)
	private byte[] imagedata;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FileEntity(int id, String name, String type, byte[] imagedata) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.imagedata = imagedata;
	}

	public byte[] getImagedata() {
		return imagedata;
	}

	public void setImagedata(byte[] bs) {
		this.imagedata = bs;
	}

	public FileEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
