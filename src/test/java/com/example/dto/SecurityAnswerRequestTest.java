package com.example.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityAnswerRequestTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        SecurityAnswerRequest request = new SecurityAnswerRequest();

        // Assert
        assertNull(request.getEmail(), "Email should be null for default constructor");
        assertNull(request.getAnswer(), "Answer should be null for default constructor");
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        String email = "user@example.com";
        String answer = "My favorite color is blue";

        // Act
        SecurityAnswerRequest request = new SecurityAnswerRequest(email, answer);

        // Assert
        assertEquals(email, request.getEmail(), "Email should match the provided value");
        assertEquals(answer, request.getAnswer(), "Answer should match the provided value");
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        SecurityAnswerRequest request = new SecurityAnswerRequest();
        String email = "user@example.com";
        String answer = "My favorite color is blue";

        // Act
        request.setEmail(email);
        request.setAnswer(answer);

        // Assert
        assertEquals(email, request.getEmail(), "Email should be set and retrieved correctly");
        assertEquals(answer, request.getAnswer(), "Answer should be set and retrieved correctly");
    }

    @Test
    public void testSetEmail() {
        // Arrange
        SecurityAnswerRequest request = new SecurityAnswerRequest();
        String email = "user@example.com";

        // Act
        request.setEmail(email);

        // Assert
        assertEquals(email, request.getEmail(), "Email should be set correctly");
    }

    @Test
    public void testSetAnswer() {
        // Arrange
        SecurityAnswerRequest request = new SecurityAnswerRequest();
        String answer = "My favorite color is blue";

        // Act
        request.setAnswer(answer);

        // Assert
        assertEquals(answer, request.getAnswer(), "Answer should be set correctly");
    }
}
