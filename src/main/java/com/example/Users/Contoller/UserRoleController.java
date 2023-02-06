package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.UserRoleService;
import com.example.Users.entity.UserRoleDTO;
import com.example.Users.entity.UserRoleEntity;

@RestController
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;

	@PostMapping("/userrole")
	public ResponseEntity<?> setRoleToUser(@RequestBody UserRoleDTO dto) {
		System.out.println(dto.getUserid() + dto.getRoleid());
		try {

			UserRoleEntity roleEntity = this.userRoleService.assignUserRole(dto);

			return new ResponseEntity<>(new Success("Success ", "Success", roleEntity), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Something wrong  can not set user role ", "Error "),
					HttpStatus.BAD_REQUEST);

		}
	}

}
