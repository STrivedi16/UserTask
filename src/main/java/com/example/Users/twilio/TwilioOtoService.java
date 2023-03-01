package com.example.Users.twilio;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;




@Service
public class TwilioOtoService {

	@Autowired
	private TwilioConfig config;
	
	  
	
	public static  int generateOtp()
	{
		Random random=new Random();
		int min=1000;
		int max=9999;
		
		int otp=random.nextInt(max-min)+min;
		return otp;
	}
	
	PasswordResetResponceDto responceDto=null;
	
	public void sentOtp(PasswordResetDto dto)
	{
		
		// Twilio.init(config.getAccountSid(), config.getAuthToken());
		
		try {
//		PhoneNumber to=new PhoneNumber(dto.getNumber());
//		
//		PhoneNumber from=new PhoneNumber(config.getTrialNumber());
		
		int otp=generateOtp();
		
		System.err.println("number get");
		
		String otpMessage="Dear Customer , Your otp is "+otp+" ues this to verify your Account";
		
		System.err.println("aadakdadka;dlaks;dl");
		
		Message message = Message.creator(
				
                new PhoneNumber(dto.getUserNumber()),
                new PhoneNumber(config.getTrialNumber()),
                otpMessage)
            .create();

        System.err.println(message.getSid());
		  
				}catch (Exception e) {
					
					e.printStackTrace();
					}
		
		
		
	}
	
//	public Mono<String> validateOtp()
//	{
//		
//	}
}
