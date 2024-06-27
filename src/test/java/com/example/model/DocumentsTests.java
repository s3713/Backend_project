package com.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class DocumentsTests {

    @Test
    public void testDocumentEntity() {
        // Create a LoanApplication (assuming LoanApplication entity and repository are set up)
        LoanApplication loanApplication = new LoanApplication();
//        loanApplication.setId(1L);  // Replace with actual ID generation or initialization

        // Create a Document
        Documents document = new Documents(1L, Documents.DocumentType.Aadhar,
                Documents.DocumentStatus.OK, "http://example.com/document.pdf",
                LocalDateTime.now(), loanApplication);

        // Mocking the behavior of a repository (if needed)
        // For this example, mocking is not necessary since we're not using a repository

        // Perform assertions directly on the document instance
        Assertions.assertEquals(1L, document.getDocument_id());
        Assertions.assertEquals(Documents.DocumentType.Aadhar, document.getDocument_type());
        Assertions.assertEquals(Documents.DocumentStatus.OK, document.getDocument_status());
        Assertions.assertEquals("http://example.com/document.pdf", document.getDocument_url());

        // Test PrePersist and PreUpdate callbacks
        Assertions.assertEquals(Documents.DocumentStatus.OK, document.getDocument_status());
        Assertions.assertNotNull(document.getUpdated_at());
    }
}
