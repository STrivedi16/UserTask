package com.example.Users.RozerPay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RozerConfig {

	private static final String ROZORPAY_API_KEY="rzp_test_pfVrYPy5z4AUsZ";
	
	private static final String ROZORPAY_API_SECRET="nLhX40f3ShoFlQu11xFElJ3e";
	
	@Bean
	public RazorpayClient client() throws RazorpayException
	{
		return new RazorpayClient(ROZORPAY_API_KEY, ROZORPAY_API_SECRET);
	}
	
	
	
}
