package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class LoanProducts {

    public LoanProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanProducts(long product_id, ProductName product_name, float product_interest_rate,
			float product_processing_fee, Vendors vendor, int product_prepayment_charge,
			String product_prepayment_conditions) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_interest_rate = product_interest_rate;
		this.product_processing_fee = product_processing_fee;
		this.vendor = vendor;
		this.product_prepayment_charge = product_prepayment_charge;
		this.product_prepayment_conditions = product_prepayment_conditions;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public ProductName getProduct_name() {
		return product_name;
	}

	public void setProduct_name(ProductName product_name) {
		this.product_name = product_name;
	}

	public float getProduct_interest_rate() {
		return product_interest_rate;
	}

	public void setProduct_interest_rate(float product_interest_rate) {
		this.product_interest_rate = product_interest_rate;
	}

	public float getProduct_processing_fee() {
		return product_processing_fee;
	}

	public void setProduct_processing_fee(float product_processing_fee) {
		this.product_processing_fee = product_processing_fee;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	public int getProduct_prepayment_charge() {
		return product_prepayment_charge;
	}

	public void setProduct_prepayment_charge(int product_prepayment_charge) {
		this.product_prepayment_charge = product_prepayment_charge;
	}

	public String getProduct_prepayment_conditions() {
		return product_prepayment_conditions;
	}

	public void setProduct_prepayment_conditions(String product_prepayment_conditions) {
		this.product_prepayment_conditions = product_prepayment_conditions;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;

    @Enumerated(EnumType.STRING)
    private ProductName product_name;

    private float product_interest_rate;
    private float product_processing_fee;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendors vendor;

    private int product_prepayment_charge;  // New field
    private String product_prepayment_conditions;  // New field

    public enum ProductName {
        Personal, Gold, Home
    }
}
