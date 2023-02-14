package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.RoleRepository;
import com.example.Users.Repository.UserRoleRepository;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Roles;
import com.example.Users.entity.UserRoleDto;
import com.example.Users.entity.UserRoleEntity;
import com.example.Users.entity.Users;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository repository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;

	public UserRoleEntity assignUserRole(UserRoleDto dto) throws Exception {
		Users users = this.usersRepository.findById(dto.getUserid())
				.orElseThrow(() -> new Exception("User id  is not found"));

		Roles roles = this.roleRepository.findById(dto.getRoleid()).orElseThrow(() -> new Exception("Role  not found"));

		UserRoleEntity roleEntity = new UserRoleEntity();
		roleEntity.setUsers(users);
		roleEntity.setRoles(roles);

		return this.repository.save(roleEntity);

	}
}
