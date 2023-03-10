package com.example.Users.OTP;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Contoller.EmailOtpController;

@Service
public class OtpService {

	@Autowired
	private OtpRepository otpRepository	;
	
	
	private SendOtp otp=new SendOtp();;
	
	public static  int generateOtp()
	{
		Random random=new Random();
		int min=1000;
		int max=9999;
		
		int otp=random.nextInt(max-min)+min;
		return otp;
	}
	
	public static int newotp=OtpService.generateOtp();
	
//	public static long currentTimeMillis=System.currentTimeMillis();
	public  static final long OTP_VALIDATION_TIME=5*60*1000;
//	public static Date otpRequestedTimeMillis=S;
//	public static long otpReqTime=otpRequestedTimeMillis.getTime();
//	
//	public static boolean isOtpValidate()
//	{
//
//		if(OTP_VALIDATION_TIME+otpReqTime< currentTimeMillis)
//		{
//			return false;
//			
//		}
//		return true;
//	}
	

	
	
	public SendOtp setOtpForVerify(String email)
	{
//		Calendar calendar=Calendar.getInstance();
//		calendar.add(Calendar.MINUTE, 5);

//		Date date=new Date(System.currentTimeMillis());
		SendOtp sendOtp=this.otpRepository.findByEmailIgnoreCase(email);
		
		
		//LocalDateTime expirationtime=LocalDateTime.now().plus(OTP_VALIDATION_TIME,ChronoUnit.MINUTES);
		
		
		//Date expirationtime=new Date(OTP_VALIDATION_TIME+new java.util.Date().getMinutes());
		
//		Calendar calender = Calendar.getInstance();
//		calender.add(Calendar.MINUTE, 5);
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime()+OTP_VALIDATION_TIME);
		System.err.println("Generation time"+ts);
		
		if(sendOtp !=null)
		{
		SendOtp otp=new SendOtp();
		otp.setSendOtpid(sendOtp.getSendOtpid());
		otp.setEmail(sendOtp.getEmail());
		otp.setOtp(newotp);
		
		otp.setOtpRequestTime(ts);
		
		
		return this.otpRepository.save(otp);
		}
		else
		{
			SendOtp otp=new SendOtp();
			
			otp.setEmail(email);
			otp.setOtp(newotp);
			
			otp.setOtpRequestTime(ts);
			
			
			return this.otpRepository.save(otp);
		}
	}
	
//	public boolean isOtpExpired(Date expirationTime) {
//        if(c)
//    }
	
	public SendOtp clearOtp(String email, int otp)
	{
		SendOtp otp2=this.otpRepository.findByEmailIgnoreCaseAndOtp(email, otp);
		
		
		
		otp2.setOtp(0);
		
		otp2.setCreationTime(null);
		otp2.setMoblie(0);
		otp2.setUpdationTime(null);
		otp2.setOtpRequestTime(null);
		
		
		return this.otpRepository.save(otp2);
	}
	
	
	public SendOtp findEmail(String email, int otp) throws Exception
	{
		
		System.err.println(email+" "+otp);
		
		
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
	
	
//	URL url2=new URL(url);
///		
//		HttpURLConnection con= (HttpURLConnection) url2.openConnection();
///		
///		con.setRequestMethod("GET");
///		
//	con.setRequestProperty("User-Agent", "Mozilla/5.0");
//	con.setRequestProperty("cache-control", "no-cache");
//		
//		int code=con.getResponseCode();
//	
//	}
}
