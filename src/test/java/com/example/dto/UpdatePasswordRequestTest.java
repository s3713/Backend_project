package com.example.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpdatePasswordRequestTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        UpdatePasswordRequest request = new UpdatePasswordRequest();

        // Assert
        assertNull(request.getEmail(), "Email should be null for default constructor");
        assertNull(request.getNewPassword(), "NewPassword should be null for default constructor");
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        String email = "user@example.com";
        String newPassword = "newPassword123";

        // Act
        UpdatePasswordRequest request = new UpdatePasswordRequest(email, newPassword);

        // Assert
        assertEquals(email, request.getEmail(), "Email should match the provided value");
        assertEquals(newPassword, request.getNewPassword(), "NewPassword should match the provided value");
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        UpdatePasswordRequest request = new UpdatePasswordRequest();
        String email = "user@example.com";
        String newPassword = "newPassword123";

        // Act
        request.setEmail(email);
        request.setNewPassword(newPassword);

        // Assert
        assertEquals(email, request.getEmail(), "Email should be set and retrieved correctly");
        assertEquals(newPassword, request.getNewPassword(), "NewPassword should be set and retrieved correctly");
    }

    @Test
    public void testSetEmail() {
        // Arrange
        UpdatePasswordRequest request = new UpdatePasswordRequest();
        String email = "user@example.com";

        // Act
        request.setEmail(email);

        // Assert
        assertEquals(email, request.getEmail(), "Email should be set correctly");
    }

    @Test
    public void testSetNewPassword() {
        // Arrange
        UpdatePasswordRequest request = new UpdatePasswordRequest();
        String newPassword = "newPassword123";

        // Act
        request.setNewPassword(newPassword);

        // Assert
        assertEquals(newPassword, request.getNewPassword(), "NewPassword should be set correctly");
    }
}
