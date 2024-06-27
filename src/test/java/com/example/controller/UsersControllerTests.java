package com.example.controller;

import com.example.model.EmailDetails;
import com.example.model.OTPVerificationRequest;
import com.example.model.Users;
import com.example.service.EmailService;
import com.example.service.UsersService;
import com.example.dto.SecurityAnswerRequest;
import com.example.dto.UpdatePasswordRequest;
import com.example.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UsersController usersController;

    private Users testUser;

    @BeforeEach
    void setUp() {
        testUser = new Users();
        testUser.setUser_id(1L);
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        testUser.setRole(Users.RoleEnum.CUSTOMER);
    }

    @Test
    void testRead() {
        List<Users> usersList = new ArrayList<>();
        usersList.add(testUser);
        when(usersService.getAllUsers()).thenReturn(usersList);

        List<Users> result = usersController.read();

        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0).getEmail());
        verify(usersService, times(1)).getAllUsers();
    }

    @Test
    void testReadOne() throws ResourceNotFoundException {
        when(usersService.getUsersById(1L)).thenReturn(testUser);

        Users result = usersController.readOne(1L);

        assertEquals("test@example.com", result.getEmail());
        verify(usersService, times(1)).getUsersById(1L);
    }

    @Test
    void testAdd() {
        when(usersService.addUsers(any(Users.class))).thenReturn(testUser);

        Users result = usersController.add(testUser);

        assertEquals("test@example.com", result.getEmail());
        verify(usersService, times(1)).addUsers(any(Users.class));
    }

    @Test
    void testUpdate() throws ResourceNotFoundException {
        doNothing().when(usersService).updateUsers(anyLong(), any(Users.class));

        usersController.update(testUser, 1L);

        verify(usersService, times(1)).updateUsers(eq(1L), any(Users.class));
    }

    @Test
    void testDelete() throws ResourceNotFoundException {
        doNothing().when(usersService).deleteUsers(1L);

        usersController.delete(1L);

        verify(usersService, times(1)).deleteUsers(1L);
    }

    @Test
    void testRegisterUser() {
        when(usersService.addUsers(any(Users.class))).thenReturn(testUser);

        Users result = usersController.registerUser(testUser);

        assertEquals("test@example.com", result.getEmail());
        assertEquals(Users.RoleEnum.CUSTOMER, result.getRole());
        verify(usersService, times(1)).addUsers(any(Users.class));
    }

    @Test
    void testLoginUser() {
        when(usersService.authenticate(anyString(), anyString())).thenReturn(Optional.of(testUser));

        ResponseEntity<?> response = usersController.loginUser(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Users);
        verify(usersService, times(1)).authenticate(anyString(), anyString());
    }

    @Test
    void testLoginUserInvalidCredentials() {
        when(usersService.authenticate(anyString(), anyString())).thenReturn(Optional.empty());

        ResponseEntity<?> response = usersController.loginUser(testUser);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Email or Password is wrong.", response.getBody());
        verify(usersService, times(1)).authenticate(anyString(), anyString());
    }

    @Test
    void testSendMail() {
        EmailDetails details = new EmailDetails();
        when(emailService.sendOtp(any(EmailDetails.class))).thenReturn("OTP Sent");

        String status = usersController.sendMail(details);

        assertEquals("OTP Sent", status);
        verify(emailService, times(1)).sendOtp(any(EmailDetails.class));
    }
    
    @Test
    void testVerifyOtp() {
        OTPVerificationRequest otpRequest = new OTPVerificationRequest();
        otpRequest.setEmail("test@example.com");
        otpRequest.setOtp("123456");

        when(emailService.verifyOtp(anyString(), anyString())).thenReturn(true);

        String result = usersController.verifyOtp(otpRequest);

        assertEquals("OTP Verified Successfully", result);
        verify(emailService, times(1)).verifyOtp(anyString(), anyString());
    }

    @Test
    void testVerifyOtpInvalid() {
        OTPVerificationRequest otpRequest = new OTPVerificationRequest();
        otpRequest.setEmail("test@example.com");
        otpRequest.setOtp("123456");

        when(emailService.verifyOtp(anyString(), anyString())).thenReturn(false);

        String result = usersController.verifyOtp(otpRequest);

        assertEquals("Invalid OTP", result);
        verify(emailService, times(1)).verifyOtp(anyString(), anyString());
    }

    @Test
    void testGetSecurityQuestion() {
        String securityQuestion = "What is your favorite color?";
        when(usersService.getSecurityQuestion(anyString())).thenReturn(securityQuestion);

        EmailDetails details = new EmailDetails();
        details.setRecipient("test@example.com");

        ResponseEntity<String> response = usersController.getSecurityQuestion(details);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(securityQuestion, response.getBody());
        verify(usersService, times(1)).getSecurityQuestion(anyString());
    }

    @Test
    void testVerifySecurityAnswer() {
        SecurityAnswerRequest answerRequest = new SecurityAnswerRequest();
        answerRequest.setEmail("test@example.com");
        answerRequest.setAnswer("Blue");

        when(usersService.verifySecurityAnswer(anyString(), anyString())).thenReturn(true);

        ResponseEntity<String> response = usersController.verifySecurityAnswer(answerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Security Answer Verified Successfully", response.getBody());
        verify(usersService, times(1)).verifySecurityAnswer(anyString(), anyString());
    }

    @Test
    void testUpdatePassword() {
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
        updatePasswordRequest.setEmail("test@example.com");
        updatePasswordRequest.setNewPassword("newpassword");

        when(usersService.updatePassword(anyString(), anyString())).thenReturn(true);

        ResponseEntity<String> response = usersController.updatePassword(updatePasswordRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password Reset Successfully", response.getBody());
        verify(usersService, times(1)).updatePassword(anyString(), anyString());
    }

    @Test
    void testLoginWithOTP() {
        OTPVerificationRequest otpRequest = new OTPVerificationRequest();
        otpRequest.setEmail("test@example.com");
        otpRequest.setOtp("123456");

        when(usersService.verifyOtpAndLogin(anyString(), anyString())).thenReturn(Optional.of(testUser));

        ResponseEntity<?> response = usersController.loginWithOTP(otpRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Optional);
        verify(usersService, times(1)).verifyOtpAndLogin(anyString(), anyString());
    }

    @Test
    void testReadEmail() throws ResourceNotFoundException {
        when(usersService.getEmailById(1L)).thenReturn("test@example.com");

        ResponseEntity<String> response = usersController.readEmail(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test@example.com", response.getBody());
        verify(usersService, times(1)).getEmailById(anyLong());
    }
}
