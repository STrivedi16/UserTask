package com.example.Users.Service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class EmailService {

//	@Value("${spring.mail.username}")
//	private String email;
//	
//	@Value("${spring.mail.password}")
//	private String password;
//	
	@Value("${spring.mail.host}")
	private String host;
	
	
	@Value("${spring.mail.port}")
	private int port;
	public boolean sendEmail(String subject, String message, String to)
	{
		boolean f= false;
		
		String from="shubhamtrivedi@nimapinfotech.com";
		
		
		
		
			// get the system properties
		Properties properties=System.getProperties();
		System.out.println("Properties"+properties);
		
		
		// set infromation to properties
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		
		// step 1 : to get session object
		
		Session session=Session.getInstance(properties,new Authenticator() {
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				
				return  new  PasswordAuthentication("shubhamtrivedi@nimapinfotech.com", "shubhamT2711$");
			}
		
			
		});
		
		//session.setDebug(true);
		
		// step2 : Compose the message 
		MimeMessage message2=new MimeMessage(session);
		
		try {
			
			message2.setFrom(from);
			message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message2.setSubject(subject);
			message2.setText(message);
			
			
			//step 3: send the message using transport class 	
			Transport.send(message2);
				
			f=true;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}
	
}
