package com.example.Users.Contoller;

import java.util.Random;

import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.OTP.OtpService;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.SuccessFileMessage;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.EmailService;
import com.example.Users.entity.Users;

@RestController
public class EmailOtpController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private UsersRepository usersRepository;
	
//	static String num="0123456789";
//	static Random random=new Random();
//	public static char[] generateOtp(int length, char[] otp)
//	{
//		for(int i=0;i<length;i++)
//		{
//			otp[i]=num.charAt(random.nextInt(10));
//		}
//		
//		return otp;
//	}
	
	
	
	
	public static String to;
	
	@GetMapping("/forgot")
	public ResponseEntity<?> getOtp(
			@RequestParam(name = "Email",required = false)String email)
	{
		try {
			
//			String message="OTP = "+EmailOtpController.generateOtp(4, new char[4]);
//			System.err.println(EmailOtpController.generateOtp(4, new char[4]));
			
			if(email.isEmpty())
			{
			
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.INVALID_EMAIL, ErrorMessageKey.USER_E031106),HttpStatus.BAD_REQUEST);
			}
			Users users=this.usersRepository.findByEmailIgnoreCase(email);
			
			if(users ==null)
			{
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.INVALID_EMAIL, ErrorMessageKey.USER_E031106),HttpStatus.BAD_REQUEST);
			}
			//OTP = "+otpService.newotp
			String message="<h3>Hello "+ " "+users.getName() +"</h3><br>"+"<h2> OTP"+" "+otpService.newotp+"</h2> <br>"
					+"<p><h6>this otp is for vrtification </h6></p>"
					+"<p><h6>this is auto generated mail please do not replay on this mail</h6></p>";
			
			
			String subject="OTP for verification";
			to=email;
			
			this.emailService.sendEmail(subject, message, to);
			
			this.otpService.setOtpForVerify(email);
			
			return new ResponseEntity<>(new SuccessFileMessage(SuccessMessageConstant.OTP_SENT, SuccessMessageKey.OTP_M032101),HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.OTP_SENT_FAIL, ErrorMessageKey.OTP_E032101),HttpStatus.BAD_REQUEST);
					
		}
	}
	
//	
//	@PostMapping("/mobile")
//	public ResponseEntity<?> setToMobile(@RequestBody long mobile)
//	
//	{
//	
//		System.out.println(mobile);
//		try {
//			
//			String message="OTP = "+otpService.newotp;
//			
//			this.otpService.sendSms(message, mobile);
//			
//			return new ResponseEntity<>(new SuccessFileMessage("Success", "Success"),HttpStatus.OK);
//			
//			
//		} catch (Exception e) {
//			
//			return new ResponseEntity<>(new ErrorMessage("Error", "Error"),HttpStatus.BAD_REQUEST);
//		}
//	}
//	
	
}
