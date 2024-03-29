package com.example.Users;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import com.example.Users.twilio.TwilioConfig;
//import com.twilio.Twilio;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling

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

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public HttpClient httpClient() {
	  return HttpClientBuilder.create().build();
	}

}
