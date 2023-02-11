package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Config.JwtRefreshToken;
import com.example.Users.Config.JwtTokenUtil;
import com.example.Users.Model.JwtRequest;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Responce.SuccessMessageToken;
import com.example.Users.Service.CustomerUserdetailsService;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerUserdetailsService customerUserdetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtRefreshToken jwtRefreshToken;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generatetoken(@RequestBody JwtRequest jwtRequest) {
		System.err.println(jwtRequest.getUsername());

		if (jwtRequest.getUsername().isEmpty() || jwtRequest.getPassword().isEmpty()) {
			if (jwtRequest.getUsername().isEmpty() && jwtRequest.getPassword().isEmpty() == false) {

				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.USERNAME_PASSWORD_INVALID, ErrorMessageKey.USER_E031101),
						HttpStatus.BAD_REQUEST);
			} else if (jwtRequest.getPassword().isEmpty() && jwtRequest.getUsername().isEmpty() == false) {
				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.USERNAME_PASSWORD_INVALID, ErrorMessageKey.USER_E031101),
						HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.USERNAME_PASSWORD_INVALID, ErrorMessageKey.USER_E031101),
						HttpStatus.BAD_REQUEST);
			}

		} else {

			try {

				this.authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

			} catch (Exception e) {

				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.USERNAME_PASSWORD_INVALID, ErrorMessageKey.USER_E031101),
						HttpStatus.BAD_REQUEST);

			}
		}

		UserDetails details = this.customerUserdetailsService.loadUserByUsername(jwtRequest.getUsername());

		String token = this.jwtTokenUtil.generateToken(details);

		String reftoken = this.jwtRefreshToken.generatereftoken(details);

		System.out.println(token);

		System.out.println();

		System.out.println(reftoken);

		return new ResponseEntity<>(
				new SuccessMessageToken(SuccessMessageConstant.LOGIN, SuccessMessageKey.LOGIN_M031100, token, reftoken),
				HttpStatus.OK);
	}

}
