package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long document_id;

    public Documents() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Documents(long document_id, DocumentType document_type, DocumentStatus document_status, String document_url,
			LocalDateTime updated_at, LoanApplication loanApplication) {
		super();
		this.document_id = document_id;
		this.document_type = document_type;
		this.document_status = document_status;
		this.document_url = document_url;
		this.updated_at = updated_at;
		this.loanApplication = loanApplication;
	}

	public long getDocument_id() {
		return document_id;
	}

	public void setDocument_id(long document_id) {
		this.document_id = document_id;
	}

	public DocumentType getDocument_type() {
		return document_type;
	}

	public void setDocument_type(DocumentType document_type) {
		this.document_type = document_type;
	}

	public DocumentStatus getDocument_status() {
		return document_status;
	}

	public void setDocument_status(DocumentStatus document_status) {
		this.document_status = document_status;
	}

	public String getDocument_url() {
		return document_url;
	}

	public void setDocument_url(String document_url) {
		this.document_url = document_url;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}

	@Enumerated(EnumType.STRING)
    private DocumentType document_type;

    @Enumerated(EnumType.STRING)
    private DocumentStatus document_status;

    private String document_url;

    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private LoanApplication loanApplication;

    @PrePersist
    protected void onCreate() {
        document_status = DocumentStatus.OK;
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }

    public enum DocumentType {
        Aadhar,
        PAN,
        Bank_Statement
    }

    public enum DocumentStatus {
        OK,
        NotOK
    }
}
