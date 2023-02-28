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

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessFileMessage;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.RolePermissionService;
import com.example.Users.entity.RolePermissionDto;
import com.example.Users.entity.RolePermissionEntity;

@RestController
public class RolePermissionController {

	@Autowired
	private RolePermissionService rolePermissionService;

	@PostMapping("/rolepermission")
	@PreAuthorize("hasAuthority('RolePermission')")	//RolePermission
	public ResponseEntity<?> setRolePermission(@RequestBody RolePermissionDto dto) {
		System.out.println(dto.getPermissionid() + "" + dto.getRoleid());

		try {

			RolePermissionEntity entity = this.rolePermissionService.setRolePermission(dto);

			return new ResponseEntity<>(new Success(SuccessMessageConstant.ROLE_PERMISSION_ADDED,
					SuccessMessageKey.ROLE_PERMISSION_M031801, entity), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.ROLE_PERMISSION_NOT_STORED,
					ErrorMessageKey.ROlE_PERMISSION_E031801), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/rolePermission/{id}")
	@PreAuthorize("hasAuthority	('Delete')")
	public ResponseEntity<?> delete(@PathVariable("id") int id )
	{

		try
		{
			this.rolePermissionService.deleteRolePermission(id);
			
			return new ResponseEntity<>(new SuccessFileMessage(SuccessMessageConstant.DELETED, SuccessMessageKey.ROLE_PERMISSION_M031801),HttpStatus.OK);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Role not deleted", "error"),HttpStatus.BAD_REQUEST);
		}
		
	}

}
