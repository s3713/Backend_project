package com.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsersTests {

    @Mock
    private EntityManager entityManagerMock;

    @Mock
    private EntityManagerFactory entityManagerFactoryMock;

    @InjectMocks
    private Users user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        user = new Users();
        user.setUser_id(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFirst_name("John");
        user.setLast_name("Doe");
        user.setPhone("1234567890");
        user.setAddress("123 Main St");
        user.setPin("12345");
        user.setSecurityQuestion(Users.SecurityQuestion.FIRST_SCHOOL);
        user.setSecurityAnswer("answer");
        user.setPan("ABCDE1234F");
        user.setDob(LocalDate.of(1990, 1, 1));
        user.setAnnualIncome(100000);
        user.setRole(Users.RoleEnum.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setLoanApplications(new HashSet<>());
    }

    @Test
    public void testUserIdGetterAndSetter() {
        assertEquals(1L, user.getUser_id());
    }

    @Test
    public void testEmailGetterAndSetter() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        assertEquals("password", user.getPassword());
    }

    // Add more test methods for other getters and setters

    // Example of how to test EntityManager and EntityManagerFactory interactions
    @Test
    public void testEntityManagerInteractions() {
        when(entityManagerFactoryMock.createEntityManager()).thenReturn(entityManagerMock);

        EntityManager entityManager = entityManagerFactoryMock.createEntityManager();
        assertEquals(entityManagerMock, entityManager);

        // Example: verify interactions
        verify(entityManagerFactoryMock, times(1)).createEntityManager();
        verifyNoMoreInteractions(entityManagerFactoryMock);
    }
}
