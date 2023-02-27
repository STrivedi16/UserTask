package com.example.Users.OTP;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class SendOtp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int  sendOtpid;
	
	@Column(length = 4)
	private int otp;
	
	private String email;
	
	private long moblie;
	
	//public  static final long OTP_VALIDATION_TIME=5*60*1000;
	
	
	private  static  java.util.Date otpReqestTime;
	
	@CreationTimestamp
	public  Timestamp creationTime;
	
	@UpdateTimestamp
	private Timestamp updationTime;

	

	
	
	

	public static java.util.Date getOtpReqestTime() {
		return otpReqestTime;
	}

	public static void setOtpReqestTime(java.util.Date date) {
		SendOtp.otpReqestTime = date;
	}

	public int getSendOtpid() {
		return sendOtpid;
	}

	public void setSendOtpid(int sendOtpid) {
		this.sendOtpid = sendOtpid;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMoblie() {
		return moblie;
	}

	public void setMoblie(long moblie) {
		this.moblie = moblie;
	}

	

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getUpdationTime() {
		return updationTime;
	}

	public void setUpdationTime(Timestamp updationTime) {
		this.updationTime = updationTime;
	}

	

	

	
	

	public SendOtp(int sendOtpid, int otp, String email, long moblie, Timestamp creationTime, Timestamp updationTime) {
		super();
		this.sendOtpid = sendOtpid;
		this.otp = otp;
		this.email = email;
		this.moblie = moblie;
		this.creationTime = creationTime;
		this.updationTime = updationTime;
	}

	public SendOtp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
