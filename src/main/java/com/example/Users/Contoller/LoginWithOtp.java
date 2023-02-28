package com.example.Users.Contoller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Config.JwtRefreshToken;
import com.example.Users.Config.JwtTokenUtil;
import com.example.Users.DTO.LoginOtpDto;
import com.example.Users.OTP.OtpService;
import com.example.Users.OTP.SendOtp;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Responce.SuccessMessageToken;
import com.example.Users.Service.CustomerUserDetailsService;
import com.example.Users.entity.Users;

@RestController
public class LoginWithOtp {

	@Autowired
	private OtpService otpService;
	
	//@Autowired
//	private JwtController controller;
	
	
	@Autowired
	private JwtTokenUtil  jwtTokenUtil;
	
	@Autowired
	private JwtRefreshToken jwtRefreshToken;
	
	@Autowired
	private UsersRepository repository;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	
	
	@PostMapping("/loginwithOTP")
	public ResponseEntity<?> loginWithOtp(@RequestBody LoginOtpDto dto)
	{
		try {
			System.err.print("otp aviyo");
			
			SendOtp otp=this.otpService.findEmail(dto.getEmail(), dto.getOtp());
			
			

			
			
//			Date date = new Date();
//			Timestamp ts = new Timestamp(date.getTime());
			
//			java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
			
			if(otpService.isOtpExpired(LocalDateTime.now())==false)
			{
				
				

				if(otp==null)
				{
					return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.LOGIN_FAIL, ErrorMessageKey.USER_E031100),HttpStatus.BAD_REQUEST);
				}
				
				String email=otp.getEmail();
				
				System.err.println(" user data have malse");
				
				Users users=this.repository.findByEmailIgnoreCase(email);
				
				System.out.println();
					
				UserDetails details=this.customerUserDetailsService.loadUserByUsername(email);
				
				String accessToken=this.jwtTokenUtil.generateToken(details);
				
				
				String refreshtoken=this.jwtRefreshToken.generatereftoken(details);
				
				this.otpService.clearOtp(dto.getEmail(), dto.getOtp());
				
				return new ResponseEntity<>(new SuccessMessageToken(SuccessMessageConstant.LOGIN, SuccessMessageKey.LOGIN_M031100, accessToken, refreshtoken),HttpStatus.OK);
				
			}
			
			else {
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.LOGIN_FAIL, ErrorMessageKey.USER_E031100),HttpStatus.BAD_REQUEST);

			}
			
			
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.LOGIN_FAIL, ErrorMessageKey.USER_E031100),HttpStatus.BAD_REQUEST);
		}

	}
	
	
	
	
	
}
