package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.RoleRepository;
import com.example.Users.entity.Roles;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	public Roles setrole(Roles roles) {
		return this.repository.save(roles);
	}

}
