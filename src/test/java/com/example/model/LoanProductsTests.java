package com.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoanProductsTests {

    @Test
    public void testLoanProductsEntity() {
        // Create a Vendors instance for association
        Vendors vendor = new Vendors();
        vendor.setVendor_id(1L);  // Replace with actual ID initialization

        // Create a LoanProducts instance
        LoanProducts loanProduct = new LoanProducts(1L, LoanProducts.ProductName.Personal,
                5.0f, 100.0f, vendor, 500, "Conditions for prepayment");

        // Perform assertions on the loanProduct instance
        Assertions.assertEquals(1L, loanProduct.getProduct_id());
        Assertions.assertEquals(LoanProducts.ProductName.Personal, loanProduct.getProduct_name());
        Assertions.assertEquals(5.0f, loanProduct.getProduct_interest_rate());
        Assertions.assertEquals(100.0f, loanProduct.getProduct_processing_fee());
        Assertions.assertEquals(vendor, loanProduct.getVendor());
        Assertions.assertEquals(500, loanProduct.getProduct_prepayment_charge());
        Assertions.assertEquals("Conditions for prepayment", loanProduct.getProduct_prepayment_conditions());
    }
}
