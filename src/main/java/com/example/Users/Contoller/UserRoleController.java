package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.UserRoleService;
import com.example.Users.entity.UserRoleDTO;
import com.example.Users.entity.UserRoleEntity;

@RestController
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;

	@PostMapping("/userrole")
	@PreAuthorize("hasAuthority	('addPermission')")
	public ResponseEntity<?> setRoleToUser(@RequestBody UserRoleDTO dto) {
		System.out.println(dto.getUserid() + dto.getRoleid());
		try {

			UserRoleEntity roleEntity = this.userRoleService.assignUserRole(dto);

			return new ResponseEntity<>(new Success(SuccessMessageConstant.USER_ROLE_ADDED,
					SuccessMessageKey.USER_ROLE_M031601, roleEntity), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.USER_ROLE_NOT_STORED, ErrorMessageKey.USER_ROLE_E031601),
					HttpStatus.BAD_REQUEST);

		}
	}

}
