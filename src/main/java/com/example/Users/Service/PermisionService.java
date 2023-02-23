package com.example.Users.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.DTO.PermissionsDto;
import com.example.Users.Repository.PermissionsRepository;
import com.example.Users.entity.Permissions;

@Service
public class PermisionService {

	@Autowired
	private PermissionsRepository permissionsRepository;

//	public Permissions setPermission(Permissions permissions) {
//		return this.permissionsRepository.save(permissions);
//	}

	public List<Permissions> getPermissions() {
		return this.permissionsRepository.findAll();
	}
	
	public Permissions setPermission(PermissionsDto dto)
	{
		Permissions permissions=new Permissions();
		permissions.setPermissions(dto.getPermissions());
		
		return this.permissionsRepository.save(permissions);
	}
}
