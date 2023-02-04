package com.example.Users.Contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testing {

	@RequestMapping(value = "/server", method = RequestMethod.GET)
	public String Hello() {
		return "This is the Server thast fnsdfnfbgierngqnkdnvfnu";
	}
}
