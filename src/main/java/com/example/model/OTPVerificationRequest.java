package com.example.model;

public class OTPVerificationRequest 
{
	private String email;
    private String otp;

    public String getEmail() {
        return email;
    }

    public OTPVerificationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OTPVerificationRequest(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

	public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}