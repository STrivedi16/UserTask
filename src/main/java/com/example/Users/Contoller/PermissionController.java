package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.PermisionService;
import com.example.Users.entity.Permissions;

@RestController
public class PermissionController {

	@Autowired
	private PermisionService service;

	@PostMapping("/permission")
	@PreAuthorize("hasAuthority	('addPermission')")
	public ResponseEntity<?> Setpermission(@RequestBody Permissions permissions) {
		try {

			Permissions permissions2 = this.service.SetPermission(permissions);

			return new ResponseEntity<>(new Success("success", "Success", permissions2), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Error in set permission", "Error"), HttpStatus.BAD_REQUEST);
		}
	}
}
