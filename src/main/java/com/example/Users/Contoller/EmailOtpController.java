package com.example.Users.Contoller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.SuccessFileMessage;
import com.example.Users.Service.EmailService;

@RestController
public class EmailOtpController {

	@Autowired
	private EmailService emailService;
	
//	static String num="0123456789";
	static Random random=new Random();
//	public static char[] generateOtp(int length, char[] otp)
//	{
//		for(int i=0;i<length;i++)
//		{
//			otp[i]=num.charAt(random.nextInt(10));
//		}
//		
//		return otp;
//	}
	
	public static  int generateOtp()
	{
		int min=1000;
		int max=9999;
		
		int otp=random.nextInt(max-min)+min;
		return otp;
	}
	
	
	
	@GetMapping("/forgot")
	public ResponseEntity<?> getOtp(
			@RequestParam(name = "Email",required = false)String email)
	{
		try {
			
//			String message="OTP = "+EmailOtpController.generateOtp(4, new char[4]);
//			System.err.println(EmailOtpController.generateOtp(4, new char[4]));
			
			String message="OTP = "+EmailOtpController.generateOtp();
			
			String subject="OTP for verification";
			String to=email;
			
			this.emailService.sendEmail(subject, message, to);
			
			return new ResponseEntity<>(new SuccessFileMessage("OTP SEND TO EMAIL ", "OTP HAS BEEN SENT SUCCESSFULL"),HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("OTP NOT SENT", "YOU MIGHT HAVE ENTER WRONG EMAIL"),HttpStatus.BAD_REQUEST);
					
		}
	}
	
	
}
