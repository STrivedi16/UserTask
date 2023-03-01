package com.example.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import com.example.Users.twilio.TwilioConfig;
//import com.twilio.Twilio;

@SpringBootApplication
public class UserTaskApplication {
	
//	@Autowired
//	private TwilioConfig config;
//	
//	public void initTwilio() {
//		Twilio.init(config.getAccountSid(), config.getAuthToken());
//	}

	public static void main(String[] args) {
		SpringApplication.run(UserTaskApplication.class, args);
	}

}
