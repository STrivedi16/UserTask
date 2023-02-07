package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.PermissionsRepository;
import com.example.Users.Repository.RolePermissionRepository;
import com.example.Users.Repository.RoleRepository;
import com.example.Users.entity.Permissions;
import com.example.Users.entity.RolePermissionDTO;
import com.example.Users.entity.RolePermissionEntity;
import com.example.Users.entity.Roles;

@Service
public class RolePermissionService {

	@Autowired
	private RolePermissionRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionsRepository permissionsRepository;

	public RolePermissionEntity setrolepermission(RolePermissionDTO dto) throws Exception {
		Roles roles = this.roleRepository.findById(dto.getRoleid()).orElseThrow(() -> new Exception("Role not get "));

		Permissions permissions = this.permissionsRepository.findById(dto.getPermissionid())
				.orElseThrow(() -> new Exception("Permission not get"));

		RolePermissionEntity entity = new RolePermissionEntity();

		entity.setRoles1(roles);
		entity.setPermissions(permissions);

		return this.repository.save(entity);
	}

}
