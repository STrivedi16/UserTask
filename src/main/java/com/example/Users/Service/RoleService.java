package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.DTO.RoleDto;
import com.example.Users.Repository.RoleRepository;
import com.example.Users.entity.Roles;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

//	public Roles setRole(Roles roles) {
//		return this.repository.save(roles);
//	}
	
	public Roles setRole(RoleDto dto)
	{
		Roles roles=new Roles();
		roles.setRole(dto.getRole());
		
		return this.repository.save(roles);
	}

}
