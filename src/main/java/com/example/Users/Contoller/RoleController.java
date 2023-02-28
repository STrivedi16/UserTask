package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.DTO.RoleDto;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessFileMessage;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.RoleService;
import com.example.Users.entity.Roles;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/role")
	@PreAuthorize("hasAuthority	('setRole')")
	public ResponseEntity<?> setRoles(@RequestBody RoleDto roles) {
		try {
			RoleDto roles2 = this.roleService.setRole(roles);

			return new ResponseEntity<>(
					new Success(SuccessMessageConstant.ROLE_ADD, SuccessMessageKey.ROLE_M031501, roles2),
					HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.ROLE_NOT_STORED, ErrorMessageKey.ROLE_E031501),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/role/{roleid}")
	@PreAuthorize("hasAuthority	('Delete')")
	public ResponseEntity<?> deleteRole(@PathVariable("roleid") int roleid)
	{
		try
		{
			this.roleService.deleteRole(roleid);
			
			return new ResponseEntity<>(new SuccessFileMessage(SuccessMessageConstant.DELETED, SuccessMessageKey.USER_M031102),HttpStatus.OK);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Role not deleted", "error"),HttpStatus.BAD_REQUEST);
		}
	}
}
