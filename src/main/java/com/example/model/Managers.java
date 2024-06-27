package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
public class Managers {

    public Managers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Managers(long manager_id, Users user, Vendors vendor, String assigned_customers, LocalDateTime created_at,
			LocalDateTime updated_at) {
		super();
		this.manager_id = manager_id;
		this.user = user;
		this.vendor = vendor;
		this.assigned_customers = assigned_customers;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public long getManager_id() {
		return manager_id;
	}

	public void setManager_id(long manager_id) {
		this.manager_id = manager_id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	public String getAssigned_customers() {
		return assigned_customers;
	}

	public void setAssigned_customers(String assigned_customers) {
		this.assigned_customers = assigned_customers;
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

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long manager_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
    private Vendors vendor;

    @Column(columnDefinition = "json")
    private String assigned_customers;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }
}
