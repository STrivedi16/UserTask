package com.example.Users.Contoller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.UsersService;
import com.example.Users.entity.Users;

@RestController
public class UserController {

	@Autowired
	private UsersService service;

	@PostMapping("/register")
	public ResponseEntity<?> setusers(@RequestBody Users users) {
		try {

			System.err.println(users.getEmail());
			System.err.println(users.getPassword());

			Pattern p = Pattern.compile("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");

			Matcher matcher = p.matcher(users.getPassword());

			System.out.println(users.getPassword());

			System.err.println(matcher.matches());

			if (matcher.matches()) {

				Users users2 = this.service.Register(users);

				return new ResponseEntity<>(new Success("Success", "success", users2), HttpStatus.OK);

			} else {

				return new ResponseEntity<>(new ErrorMessage(
						"Your Password is incorrect or invalid Your Need to add  Atlist One Digit , One Upper case Letter , One lowerCase letter, one Spcial charector, only 6-8 letter are allowed",
						"error"), HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Error in Creation", "Error"), HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
