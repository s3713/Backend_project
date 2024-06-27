package com.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OTPDetailsTest {

    // Since OTPDetails is a simple POJO, we don't really need to use @InjectMocks,
    // but for the sake of the example, we'll include it.
    @InjectMocks
    private OTPDetails otpDetails;

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String expectedOtp = "123456";
        LocalDateTime expectedExpiresAt = LocalDateTime.now().plusMinutes(5);

        // Act
        OTPDetails otpDetails = new OTPDetails(expectedOtp, expectedExpiresAt);

        // Assert
        assertNotNull(otpDetails);
        assertEquals(expectedOtp, otpDetails.getOtp());
        assertEquals(expectedExpiresAt, otpDetails.getExpiresAt());
    }

    @Test
    public void testOtpGetter() {
        // Arrange
        String expectedOtp = "654321";
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(10);

        // Act
        OTPDetails otpDetails = new OTPDetails(expectedOtp, expiresAt);

        // Assert
        assertEquals(expectedOtp, otpDetails.getOtp());
    }

    @Test
    public void testExpiresAtGetter() {
        // Arrange
        String otp = "987654";
        LocalDateTime expectedExpiresAt = LocalDateTime.now().plusHours(1);

        // Act
        OTPDetails otpDetails = new OTPDetails(otp, expectedExpiresAt);

        // Assert
        assertEquals(expectedExpiresAt, otpDetails.getExpiresAt());
    }

    // Add more tests if OTPDetails class evolves to include more methods or logic.
}
