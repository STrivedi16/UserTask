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
	
	private static final long OTP_VALIDATION_TIME=5*60*1000;
	
	private Date otpReqestTime;
	
	@CreationTimestamp
	private Timestamp creationTime;
	
	@UpdateTimestamp
	private Timestamp updationTime;

	

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

	public Date getOtpReqestTime() {
		return otpReqestTime;
	}

	public void setOtpReqestTime(Date otpReqestTime) {
		this.otpReqestTime = otpReqestTime;
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

	public static long getOtpValidationTime() {
		return OTP_VALIDATION_TIME;
	}

	

	public SendOtp(int sendOtpid, int otp, String email, long moblie, Date otpReqestTime, Timestamp creationTime,
			Timestamp updationTime) {
		super();
		this.sendOtpid = sendOtpid;
		this.otp = otp;
		this.email = email;
		this.moblie = moblie;
		this.otpReqestTime = otpReqestTime;
		this.creationTime = creationTime;
		this.updationTime = updationTime;
	}

	public SendOtp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
