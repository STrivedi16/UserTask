//package com.example.Users.twilio;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//public class Twiliocontroller {
//	
//	
//	 @Autowired
//	    private TwilioOtoService otpService;
//
//	    @PostMapping("/send")
//	    public ResponseEntity<String> sendOtp(@RequestParam("phone") String phone) {
//	        otpService.sendOtp(phone);
//	        return ResponseEntity.ok("OTP sent successfully");
//	    }
//
//
//}
