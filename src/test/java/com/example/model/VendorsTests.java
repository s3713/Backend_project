package com.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VendorsTests {

    @Test
    public void testVendorsEntity() {
        // Create a Vendors instance
        Vendors vendor = new Vendors(1L, "Example Vendor", "123-456-7890", "vendor@example.com", "vendor_logo.png");

        // Perform assertions on the vendor instance
        Assertions.assertEquals(1L, vendor.getVendor_id());
        Assertions.assertEquals("Example Vendor", vendor.getVendor_name());
        Assertions.assertEquals("123-456-7890", vendor.getContact_phone());
        Assertions.assertEquals("vendor@example.com", vendor.getContact_email());
        Assertions.assertEquals("vendor_logo.png", vendor.getVendor_logo());
    }
}
