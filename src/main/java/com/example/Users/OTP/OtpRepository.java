package com.example.Users.OTP;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<SendOtp, Long>{

	
	SendOtp findByEmailIgnoreCaseAndOtp(String email, int otp);
	
	SendOtp findByEmailIgnoreCase(String email);
	
}
