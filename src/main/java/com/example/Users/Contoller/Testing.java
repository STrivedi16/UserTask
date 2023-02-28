package com.example.Users.Contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testing {
	private static final Logger LOG = LoggerFactory.getLogger(Testing.class);

	@Autowired
	private TymeleafService service;
	
	@RequestMapping(value = "/server", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('setRole')")
	public String Hello() {
		return "this is the server page  ";
	}

	@GetMapping("/hello")

	@PreAuthorize("hasAuthority	('getusers')")
	public String welcome() {
		LOG.info("CONTROLLER >> getByUserById >> ");
		return "Welcome to this applicaion ";
	}
	
	@GetMapping("/generatePdf.html")
	public String teat(Model model)
	{
		
		return this.service.generateHtml();
		
	}
}
