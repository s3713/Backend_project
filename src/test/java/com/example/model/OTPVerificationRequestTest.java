package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OTPVerificationRequestTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        OTPVerificationRequest request = new OTPVerificationRequest();

        // Assert
        assertNull(request.getEmail(), "Email should be null for default constructor");
        assertNull(request.getOtp(), "OTP should be null for default constructor");
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        String email = "user@example.com";
        String otp = "654321";

        // Act
        OTPVerificationRequest request = new OTPVerificationRequest();

        // Assert
        assertEquals(email, request.getEmail(), "Email should match the provided value");
        assertEquals(otp, request.getOtp(), "OTP should match the provided value");
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        OTPVerificationRequest request = new OTPVerificationRequest();
        String email = "user@example.com";
        String otp = "654321";

        // Act
        request.setEmail(email);
        request.setOtp(otp);

        // Assert
        assertEquals(email, request.getEmail(), "Email should be set and retrieved correctly");
        assertEquals(otp, request.getOtp(), "OTP should be set and retrieved correctly");
    }

    @Test
    public void testSetEmail() {
        // Arrange
        OTPVerificationRequest request = new OTPVerificationRequest();
        String email = "user@example.com";

        // Act
        request.setEmail(email);

        // Assert
        assertEquals(email, request.getEmail(), "Email should be set correctly");
    }

    @Test
    public void testSetOtp() {
        // Arrange
        OTPVerificationRequest request = new OTPVerificationRequest();
        String otp = "654321";

        // Act
        request.setOtp(otp);

        // Assert
        assertEquals(otp, request.getOtp(), "OTP should be set correctly");
    }
}
