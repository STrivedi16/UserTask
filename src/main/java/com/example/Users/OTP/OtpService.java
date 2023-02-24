package com.example.Users.OTP;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Contoller.EmailOtpController;

@Service
public class OtpService {

	@Autowired
	private OtpRepository otpRepository	;
	
	
	
	public static  int generateOtp()
	{
		Random random=new Random();
		int min=1000;
		int max=9999;
		
		int otp=random.nextInt(max-min)+min;
		return otp;
	}
	
	public static int newotp=OtpService.generateOtp();
	
	public SendOtp setOtpForVerify(String email)
	{
		SendOtp otp=new SendOtp();
		otp.setEmail(email);
		otp.setOtp(newotp);
		
		return this.otpRepository.save(otp);
		
	}
	
	public SendOtp clearOtp(String email, int otp)
	{
		SendOtp otp2=this.otpRepository.findByEmailIgnoreCaseAndOtp(email, otp);
		
		
		
		otp2.setOtp(0);
		otp2.setEmail(null);
		otp2.setCreationTime(null);
		otp2.setMoblie(0);
		otp2.setUpdationTime(null);
		
		
		return this.otpRepository.save(otp2);
	}
	
	
	public SendOtp findEmail(String email, int otp) throws Exception
	{
		
		
		
		SendOtp otp2=this.otpRepository.findByEmailIgnoreCaseAndOtp(email, otp);
		
		if(otp2==null)
		{
			throw new Exception("not valid otp");
		}
		
		return otp2;
	}
	
	
//	public static void sendSms(String message, long mobilenumber) throws Exception
//	{
//		String apikey="UH8m9Mt7rw3GvO0WX6jpBkas4IbLKfDyFVeQPzonliTg1dRc5ZhJm2dprljyzNHYCIRX76bASgTL1nUi";
//		String senderId="FastSM";
//		message=URLEncoder.encode(message,"UTF-8");
//		String route="otp";
//		
//		String url="https://www.fast2sms.com/dev/bulkV2?authorization="+apikey+"&variables_values="+message+"&route=otp&numbers="+mobilenumber;
//		
//		URL url2=new URL(url);
//		
//		HttpURLConnection con= (HttpURLConnection) url2.openConnection();
//		
//		con.setRequestMethod("GET");
//		
//		con.setRequestProperty("User-Agent", "Mozilla/5.0");
//		con.setRequestProperty("cache-control", "no-cache");
//		
//		int code=con.getResponseCode();
//		
//	}
}
