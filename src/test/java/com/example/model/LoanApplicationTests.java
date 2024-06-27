package com.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LoanApplicationTests {

    @Test
    public void testLoanApplicationEntity() {
        // Create Users, LoanProducts, and Vendors instances for association
        Users user = new Users();
        user.setUser_id(1L);  // Replace with actual ID initialization

        LoanProducts product = new LoanProducts();
        product.setProduct_id(1L);  // Replace with actual ID initialization

        Vendors vendor = new Vendors();
        vendor.setVendor_id(1L);  // Replace with actual ID initialization

        // Create a LoanApplication instance
        LoanApplication loanApplication = new LoanApplication(1L, 10000, 12,
                LocalDateTime.now(), null, "Initial submission",
                LoanApplication.Status.Application_Submitted, user, product, vendor);

        // Perform assertions on the loanApplication instance
        Assertions.assertEquals(1L, loanApplication.getApplication_id());
        Assertions.assertEquals(10000, loanApplication.getAmount_required());
        Assertions.assertEquals(12, loanApplication.getTenure());
        Assertions.assertNotNull(loanApplication.getCreated_at());
        Assertions.assertNull(loanApplication.getUpdated_at()); // Should be null initially
        Assertions.assertEquals("Initial submission", loanApplication.getReview_message());
        Assertions.assertEquals(LoanApplication.Status.Application_Submitted, loanApplication.getStatus());
        Assertions.assertEquals(user, loanApplication.getUser());
        Assertions.assertEquals(product, loanApplication.getProduct());
        Assertions.assertEquals(vendor, loanApplication.getVendor());

        // Test PrePersist and PreUpdate callbacks
        Assertions.assertNotNull(loanApplication.getCreated_at());
        loanApplication.setStatus(LoanApplication.Status.Docs_Submitted);  // Simulate status change
        loanApplication.onUpdate();  // Trigger onUpdate manually
        Assertions.assertNotNull(loanApplication.getUpdated_at());
    }
}
