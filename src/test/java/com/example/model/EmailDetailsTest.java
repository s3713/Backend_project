package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailDetailsTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        EmailDetails emailDetails = new EmailDetails();

        // Assert
        assertNull(emailDetails.getRecipient(), "Recipient should be null for default constructor");
        assertNull(emailDetails.getMsgBody(), "MsgBody should be null for default constructor");
        assertNull(emailDetails.getSubject(), "Subject should be null for default constructor");
        assertNull(emailDetails.getAttachment(), "Attachment should be null for default constructor");
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        String recipient = "user@example.com";
        String msgBody = "This is the body of the email.";
        String subject = "Test Subject";
        String attachment = "/path/to/attachment";

        // Act
        EmailDetails emailDetails = new EmailDetails(recipient, msgBody, subject, attachment);

        // Assert
        assertEquals(recipient, emailDetails.getRecipient(), "Recipient should match the provided value");
        assertEquals(msgBody, emailDetails.getMsgBody(), "MsgBody should match the provided value");
        assertEquals(subject, emailDetails.getSubject(), "Subject should match the provided value");
        assertEquals(attachment, emailDetails.getAttachment(), "Attachment should match the provided value");
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        EmailDetails emailDetails = new EmailDetails();
        String recipient = "user@example.com";
        String msgBody = "This is the body of the email.";
        String subject = "Test Subject";
        String attachment = "/path/to/attachment";

        // Act
        emailDetails.setRecipient(recipient);
        emailDetails.setMsgBody(msgBody);
        emailDetails.setSubject(subject);
        emailDetails.setAttachment(attachment);

        // Assert
        assertEquals(recipient, emailDetails.getRecipient(), "Recipient should be set and retrieved correctly");
        assertEquals(msgBody, emailDetails.getMsgBody(), "MsgBody should be set and retrieved correctly");
        assertEquals(subject, emailDetails.getSubject(), "Subject should be set and retrieved correctly");
        assertEquals(attachment, emailDetails.getAttachment(), "Attachment should be set and retrieved correctly");
    }

    @Test
    public void testSetRecipient() {
        // Arrange
        EmailDetails emailDetails = new EmailDetails();
        String recipient = "user@example.com";

        // Act
        emailDetails.setRecipient(recipient);

        // Assert
        assertEquals(recipient, emailDetails.getRecipient(), "Recipient should be set correctly");
    }

    @Test
    public void testSetMsgBody() {
        // Arrange
        EmailDetails emailDetails = new EmailDetails();
        String msgBody = "This is the body of the email.";

        // Act
        emailDetails.setMsgBody(msgBody);

        // Assert
        assertEquals(msgBody, emailDetails.getMsgBody(), "MsgBody should be set correctly");
    }

    @Test
    public void testSetSubject() {
        // Arrange
        EmailDetails emailDetails = new EmailDetails();
        String subject = "Test Subject";

        // Act
        emailDetails.setSubject(subject);

        // Assert
        assertEquals(subject, emailDetails.getSubject(), "Subject should be set correctly");
    }

    @Test
    public void testSetAttachment() {
        // Arrange
        EmailDetails emailDetails = new EmailDetails();
        String attachment = "/path/to/attachment";

        // Act
        emailDetails.setAttachment(attachment);

        // Assert
        assertEquals(attachment, emailDetails.getAttachment(), "Attachment should be set correctly");
    }
}
