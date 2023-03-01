package com.example.Users.twilio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {

	
	
	private String accountSid;
	
	private String authToken;
	
	private String trialNumber;

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getTrialNumber() {
		return trialNumber;
	}

	public void setTrialNumber(String trialNumber) {
		this.trialNumber = trialNumber;
	}

	public TwilioConfig(String accountSid, String authToken, String trialNumber) {
		super();
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.trialNumber = trialNumber;
	}

	public TwilioConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
