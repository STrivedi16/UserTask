package com.example.Users.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tasks {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String task;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tasks")
	List<UsersTaskEntity> users;

}
