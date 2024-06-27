package com.example.service;

import com.example.model.EmailDetails;
import com.example.model.OTPDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    private Map<String, OTPDetails> otpStorage;

    @BeforeEach
    public void setup() {
        otpStorage = new HashMap<>();
//        emailService.setOtpStorage(otpStorage); // Inject the mocked OTP storage
    }

    @Test
    public void testSendSimpleMail_Success() {
        // Given
        EmailDetails emailDetails = new EmailDetails();

        // When
//        when(javaMailSender.send(any())).thenReturn(null); // Mocking the mail sender

        String result = emailService.sendSimpleMail(emailDetails);

        // Then
        assertEquals("Mail Sent Successfully...", result);
    }
    
    @Test
    public void testSendSimpleMail_Failure() throws MessagingException {
        // Given
        EmailDetails emailDetails = new EmailDetails();

        // Simulate RuntimeException when sending the email
        doThrow(new RuntimeException("Simulated error")).when(javaMailSender).send(any(MimeMessage.class));

        // When
        String result = emailService.sendSimpleMail(emailDetails);

        // Then
        assertEquals("Error while Sending Mail", result);
    }

    

    @Test
    public void testSendOtp_Success() {
        // Given
        EmailDetails emailDetails = new EmailDetails();

        // When
        String result = emailService.sendOtp(emailDetails);

        // Then
        assertTrue(result.startsWith("OTP sent successfully to "));
    }
    
    @Test
    public void testVerifyOtp_ExpiredOtp() {
        // Given
        String email = "recipient@example.com";
        String otp = "123456";
        LocalDateTime expiresAt = LocalDateTime.now().minusMinutes(1); // Set expiration time in the past

        otpStorage.put(email, new OTPDetails(otp, expiresAt));

        // When
        boolean result = emailService.verifyOtp(email, otp);

        // Then
        assertFalse(result); // Expecting false because the OTP should be expired
        assertNotNull(otpStorage.get(email)); // Ensure OTP is still in storage after verification fails
    }
}
