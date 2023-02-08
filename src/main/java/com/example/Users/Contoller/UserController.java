package com.example.Users.Contoller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Config.JwtFilter;
import com.example.Users.Interface.UsersTask;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ResourceNotFoundException;
import com.example.Users.Responce.Success;
import com.example.Users.Service.UsersService;
import com.example.Users.entity.Users;

@RestController
public class UserController {

	@Autowired
	private UsersService service;

	JwtFilter filter = new JwtFilter();

	@PostMapping("/register")
	public ResponseEntity<?> setusers(@RequestBody Users users) {

		if (users.getName().isEmpty() == false && users.getEmail().isEmpty() == false
				&& users.getPassword().isEmpty() == false) {
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
		} else {
			return new ResponseEntity<>(new ErrorMessage("You might have not enter Email, Password or Name",
					"Name , Email, Password are the Mendetory for register"), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getdata(@PathVariable("id") int id) throws Exception {
		try {

			if (id == filter.id) {
				Users users = this.service.getbyid(id);
				return new ResponseEntity<>(new Success("Success", "Success", users), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ErrorMessage("You Can not access Other Account details", "Not Access"),
						HttpStatus.BAD_REQUEST);
			}

		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Not Found"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getall")
	@PreAuthorize("hasAuthority('getusers')")
	public ResponseEntity<?> getalldata() {
		try {

			List<Users> list = this.service.getall();

			return new ResponseEntity<>(new Success("Succcess", "Success", list), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Not Found"), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/usertask/{id}")
	@PreAuthorize("hasAuthority('ShowTask')")
	public ResponseEntity<?> getusertask(@PathVariable("id") int id) {
		try {

			List<UsersTask> list = this.service.getusertask(id);

			return new ResponseEntity<>(new Success("Success", "Success", list), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Not Found"), HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/mytask/{id}")
	public ResponseEntity<?> showusertask(@PathVariable("id") int id) {
		try {

			if (id == filter.id) {

				List<UsersTask> list = this.service.getusertask(id);

				return new ResponseEntity<>(new Success("Success", "Success", list), HttpStatus.OK);

			}

			else {

				return new ResponseEntity<>(new ErrorMessage("You Don't Have Access to show other task", "Error"),
						HttpStatus.BAD_REQUEST);
			}
		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Not Found"), HttpStatus.NOT_FOUND);

		}
	}

}
