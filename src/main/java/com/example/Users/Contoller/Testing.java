package com.example.Users.Contoller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testing {

	@RequestMapping(value = "/server", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('setRole')")
	public String Hello() {
		return "This is the Server thast fnsdfnfbgierngqnkdnvfnu";
	}

	@GetMapping("/hello")
	@PreAuthorize("hasAuthority	('getusers')")
	public String welcome() {
		return "Welcome to this applicaion ";
	}
}
