package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.RoleService;
import com.example.Users.entity.Roles;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/role")
	public ResponseEntity<?> setroles(@RequestBody Roles roles) {
		try {
			Roles roles2 = this.roleService.setrole(roles);

			return new ResponseEntity<>(new Success("Success", "Success", roles2), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorMessage("Error in stored in role ", "Error "), HttpStatus.BAD_REQUEST);
		}
	}
}
