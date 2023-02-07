package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.PermissionsRepository;
import com.example.Users.entity.Permissions;

@Service
public class PermisionService {

	@Autowired
	private PermissionsRepository permissionsRepository;

	public Permissions SetPermission(Permissions permissions) {
		return this.permissionsRepository.save(permissions);
	}
}
