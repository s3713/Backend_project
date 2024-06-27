package com.example.service;

import com.example.model.Users;
import com.example.repository.UsersRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.service.EmailService;
import com.example.service.UsersService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTests {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UsersService usersService;

    @Test
    public void testGetAllUsers() {
        Users user1 = new Users();
        Users user2 = new Users();
        when(usersRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        
        List<Users> usersList = usersService.getAllUsers();
        
        assertEquals(2, usersList.size());
        verify(usersRepository, times(1)).findAll();
    }

    @Test
    public void testGetUsersById_Success() throws ResourceNotFoundException {
        Users user = new Users();
        user.setUser_id(1L);
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        
        Users foundUser = usersService.getUsersById(1L);
        
        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getUser_id());
        verify(usersRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUsersById_NotFound() {
        when(usersRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            usersService.getUsersById(1L);
        });
        
        verify(usersRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddUsers() {
        Users user = new Users();
        user.setPassword("plainPassword");
        
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        
        Users createdUser = usersService.addUsers(user);
        
        assertNotNull(createdUser);
        assertTrue(new BCryptPasswordEncoder().matches("plainPassword", createdUser.getPassword()));
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUsers_Success() throws ResourceNotFoundException {
        Users existingUser = new Users();
        existingUser.setUser_id(1L);
        existingUser.setPassword("oldPassword");
        Users updatedUser = new Users();
        updatedUser.setPassword("newPassword");
        
        when(usersRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        
        usersService.updateUsers(1L, updatedUser);
        
        assertTrue(new BCryptPasswordEncoder().matches("newPassword", existingUser.getPassword()));
        verify(usersRepository, times(1)).findById(1L);
        verify(usersRepository, times(1)).save(existingUser);
    }

    @Test
    public void testDeleteUsers_Success() throws ResourceNotFoundException {
        Users user = new Users();
        user.setUser_id(1L);
        
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        
        usersService.deleteUsers(1L);
        
        verify(usersRepository, times(1)).findById(1L);
        verify(usersRepository, times(1)).delete(user);
    }

    @Test
    public void testAuthenticate_Success() {
        Users user = new Users();
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        
        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);
        
        Optional<Users> authenticatedUser = usersService.authenticate("test@example.com", "password");
        
        assertTrue(authenticatedUser.isPresent());
        verify(usersRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testAuthenticate_Failure() {
        Users user = new Users();
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        
        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);
        
        Optional<Users> authenticatedUser = usersService.authenticate("test@example.com", "wrongpassword");
        
        assertFalse(authenticatedUser.isPresent());
        verify(usersRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testGetSecurityQuestion() {
        Users user = new Users();
        user.setSecurityQuestion(Users.SecurityQuestion.FAVOURITE_FOOD);
        
        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);
        
        String question = usersService.getSecurityQuestion("test@example.com");
        
        assertEquals("FAVOURITE_FOOD", question);
        verify(usersRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testVerifySecurityAnswer_Success() {
        Users user = new Users();
        user.setSecurityAnswer("MyAnswer");
        
        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);
        
        boolean isCorrect = usersService.verifySecurityAnswer("test@example.com", "MyAnswer");
        
        assertTrue(isCorrect);
        verify(usersRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testVerifySecurityAnswer_Failure() {
        Users user = new Users();
        user.setSecurityAnswer("MyAnswer");
        
        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);
        
        boolean isCorrect = usersService.verifySecurityAnswer("test@example.com", "WrongAnswer");
        
        assertFalse(isCorrect);
        verify(usersRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testUpdatePassword_Success() {
        Users user = new Users();
        
        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);
        
        boolean result = usersService.updatePassword("test@example.com", "newPassword");
        
        assertTrue(result);
        assertTrue(new BCryptPasswordEncoder().matches("newPassword", user.getPassword()));
        verify(usersRepository, times(1)).findByEmail("test@example.com");
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    public void testUpdatePassword_Failure() {
        when(usersRepository.findByEmail("test@example.com")).thenReturn(null);
        
        boolean result = usersService.updatePassword("test@example.com", "newPassword");
        
        assertFalse(result);
        verify(usersRepository, times(1)).findByEmail("test@example.com");
        verify(usersRepository, never()).save(any(Users.class));
    }

    @Test
    public void testVerifyOtpAndLogin_Success() {
        Users user = new Users();
        when(emailService.verifyOtp("test@example.com", "otp")).thenReturn(true);
        when(usersRepository.findByEmail("test@example.com")).thenReturn(user);
        
        Optional<Users> authenticatedUser = usersService.verifyOtpAndLogin("test@example.com", "otp");
        
        assertTrue(authenticatedUser.isPresent());
        verify(emailService, times(1)).verifyOtp("test@example.com", "otp");
        verify(usersRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testVerifyOtpAndLogin_Failure() {
        when(emailService.verifyOtp("test@example.com", "otp")).thenReturn(false);
        
        Optional<Users> authenticatedUser = usersService.verifyOtpAndLogin("test@example.com", "otp");
        
        assertFalse(authenticatedUser.isPresent());
        verify(emailService, times(1)).verifyOtp("test@example.com", "otp");
        verify(usersRepository, never()).findByEmail("test@example.com");
    }

    @Test
    public void testGetEmailById_Success() throws ResourceNotFoundException {
        when(usersRepository.findEmailById(1L)).thenReturn("test@example.com");
        
        String email = usersService.getEmailById(1L);
        
        assertEquals("test@example.com", email);
        verify(usersRepository, times(1)).findEmailById(1L);
    }

    @Test
    public void testGetEmailById_NotFound() {
        when(usersRepository.findEmailById(1L)).thenReturn(null);
        
        assertThrows(ResourceNotFoundException.class, () -> {
            usersService.getEmailById(1L);
        });
        
        verify(usersRepository, times(1)).findEmailById(1L);
    }
}
