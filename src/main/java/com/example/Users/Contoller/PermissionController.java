package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.DTO.PermissionsDto;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.PermisionService;
import com.example.Users.entity.Permissions;

@RestController
public class PermissionController {

	@Autowired
	private PermisionService service;

	@PostMapping("/permission")
	@PreAuthorize("hasAuthority	('addPermission')")
	public ResponseEntity<?> setPermission(@RequestBody PermissionsDto permissions) {
		try {

			Permissions permissions2 = this.service.setPermission(permissions);

			return new ResponseEntity<>(new Success(SuccessMessageConstant.PERMISSION_STORED,
					SuccessMessageKey.PERMISSION_M0313701, permissions2), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.PERMISSION_NOT_STORED, ErrorMessageKey.PERMISSION_E031701),
					HttpStatus.BAD_REQUEST);
		}
	}
}
