package com.example.model;

import java.time.LocalDateTime;

public class OTPDetails 
{
	 public OTPDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OTPDetails(String otp, LocalDateTime expiresAt) {
		super();
		this.otp = otp;
		this.expiresAt = expiresAt;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	private String otp;
     private LocalDateTime expiresAt;

     public String getOtp() {
         return otp;
     }

     public LocalDateTime getExpiresAt() {
         return expiresAt;
     }
 }


