package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.QueryService;
import com.example.Users.entity.QueryPortal;
import com.example.Users.entity.QueryPortalDto;

@RestController
public class QueryPortalContoller {

	@Autowired
	private QueryService queryService;

	@PostMapping("/ask")
	public ResponseEntity<?> setQuery(@RequestBody QueryPortalDto dto) {
		try {

			QueryPortal portal = this.queryService.setQuery(dto);

			return new ResponseEntity<>(new Success("Success", "Success", portal), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Error in asking query ", "error"), HttpStatus.BAD_REQUEST);
		}
	}

}
