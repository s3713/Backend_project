package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity

@Table(name = "loan_application", indexes = {
    @Index(name = "idx_user_id", columnList = "user_id"),
    @Index(name = "idx_product_id", columnList = "product_id"),
    @Index(name = "idx_vendor_id", columnList = "vendor_id")
})
public class LoanApplication {

    public LoanApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanApplication(long application_id, int amount_required, int tenure, LocalDateTime created_at,
			LocalDateTime updated_at, String review_message, Status status, Users user, LoanProducts product,
			Vendors vendor) {
		super();
		this.application_id = application_id;
		this.amount_required = amount_required;
		this.tenure = tenure;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.review_message = review_message;
		this.status = status;
		this.user = user;
		this.product = product;
		this.vendor = vendor;
	}

	public long getApplication_id() {
		return application_id;
	}

	public void setApplication_id(long application_id) {
		this.application_id = application_id;
	}

	public int getAmount_required() {
		return amount_required;
	}

	public void setAmount_required(int amount_required) {
		this.amount_required = amount_required;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public String getReview_message() {
		return review_message;
	}

	public void setReview_message(String review_message) {
		this.review_message = review_message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public LoanProducts getProduct() {
		return product;
	}

	public void setProduct(LoanProducts product) {
		this.product = product;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long application_id;

    private int amount_required;
    private int tenure;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String review_message;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private LoanProducts product;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
    private Vendors vendor;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }

    public enum Status {
        Application_Submitted,
        Submit_Docs,
        Docs_Submitted,
        Resubmit_Docs,
        Docs_Resubmitted,
        Loan_Approved,
        Final
    }
}
