package com.example.Users.Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class PasswordVerification {

	
	public boolean passwordverify(String password)
	{
		Pattern p = Pattern.compile("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");

		Matcher matcher = p.matcher(password);

		System.err.println(matcher.matches());
		
		if(matcher.matches())
		{
			return true;
		}
		else {
			return false;
		}
		
	}

	
}
