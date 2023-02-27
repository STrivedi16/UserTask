package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.DTO.LoginOtpDto;
import com.example.Users.OTP.OtpService;
import com.example.Users.OTP.SendOtp;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

//@RestController
public class OtpVerification {

	@Autowired
	private OtpService otpService;
	
	
	
	public   boolean verifyOtp(@RequestBody LoginOtpDto dto) throws Exception
	{
		try {
			
			
			
			SendOtp otp=this.otpService.findEmail(dto.getEmail(), dto.getOtp());
			
			if(otp == null)
			{
				return false;
			}
			else {
				return true;
			}
			
			
			
		} catch (Exception e) {
		
			throw new Exception("Not verify");
		}
	}
	
	
}
