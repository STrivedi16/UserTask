package com.example.Users.twilio;

import java.util.Random;
//
//
//
//import org.springframework.stereotype.Service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//@Service
//public class TwilioOtoService {
//
//	
//	
//	  
//	
//	public static  int generateOtp()
//	{
//		Random random=new Random();
//		int min=1000;
//		int max=9999;
//		
//		int otp=random.nextInt(max-min)+min;
//		return otp;
//	}
//	
//	private final String ACCOUNT_SID = "ACa68022a9760a0160eb5139a4ce9677a3";
//    private final String AUTH_TOKEN = "df9dbb9ac776409360117fc3be3e79a8";
//    private final String FROM_PHONE_NUMBER = "+12762623617";
//    
//    public void sendOtp(String toPhoneNumber) {
//        int otp = generateOtp();
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new PhoneNumber(toPhoneNumber),
//                new PhoneNumber(FROM_PHONE_NUMBER),
//                "Your OTP is: " + otp)
//            .create();
//        System.out.println("OTP sent: " + message.getSid());
//    }
//	
////	public Mono<String> validateOtp()
////	{
////		
////	}
//}
