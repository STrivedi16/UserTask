package com.example.Users.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Repository.UsersRepository;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.CustomerUserDetailsService;
import com.example.Users.entity.Users;

@RestController
public class GenerateAccessToken {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtRefreshToken jwtRefreshToken;

	@Autowired
	private UsersRepository repository;

	@Autowired
	private CustomerUserDetailsService customerUserdetailsService;

	@GetMapping("/generatetoken")
	public ResponseEntity<?> GeneratenewToken(
			@RequestParam(name = "RefreshToken", required = false) String reftoken)
			throws Exception {
		System.out.println(reftoken);

		String type = this.jwtRefreshToken.getTypeFromToken(reftoken);

		if (type.equalsIgnoreCase("refresh")) {

			String username = this.jwtRefreshToken.getUsernameFromToken(reftoken);

			Users users = this.repository.findByEmailIgnoreCase(username);

			if (users == null)
				throw new Exception("Value not found");

			UserDetails details = this.customerUserdetailsService.loadUserByUsername(users.getEmail());

			String token = this.jwtTokenUtil.generateToken(details);

			System.err.println(token);

			return new ResponseEntity<>(new Success("Success", "Success", token), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorMessage("Invalid token", "Invaid token "), HttpStatus.BAD_REQUEST);
		}
	}

}
