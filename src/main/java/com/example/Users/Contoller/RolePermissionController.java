package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.RolePermissionService;
import com.example.Users.entity.RolePermissionDTO;
import com.example.Users.entity.RolePermissionEntity;

@RestController
public class RolePermissionController {

	@Autowired
	private RolePermissionService rolePermissionService;

	@PostMapping("/rolepermission")
	public ResponseEntity<?> setRolePermission(@RequestBody RolePermissionDTO dto) {
		System.out.println(dto.getPermissionid() + "" + dto.getRoleid());

		try {

			RolePermissionEntity entity = this.rolePermissionService.setrolepermission(dto);

			return new ResponseEntity<>(new Success(SuccessMessageConstant.ROLE_PERMISSION_ADDED,
					SuccessMessageKey.ROLE_PERMISSION_M031801, entity), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.ROLE_PERMISSION_NOT_STORED,
					ErrorMessageKey.ROlE_PERMISSION_E031801), HttpStatus.BAD_REQUEST);
		}
	}

}
