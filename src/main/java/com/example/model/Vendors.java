package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
public class Vendors {

	public Vendors() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendors(long vendor_id, String vendor_name, String contact_phone, String contact_email, String vendor_logo) {
		super();
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.contact_phone = contact_phone;
		this.contact_email = contact_email;
		this.vendor_logo = vendor_logo;
	}
	public long getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(long vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getVendor_logo() {
		return vendor_logo;
	}
	public void setVendor_logo(String vendor_logo) {
		this.vendor_logo = vendor_logo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vendor_id;

	private String vendor_name;
	private String contact_phone;
	private String contact_email;
	private String vendor_logo;

//	@OneToMany(mappedBy = "vendor")
//	private Set<LoanProducts> loanProducts;
//
//	@OneToMany(mappedBy = "vendor")
//	private Set<Managers> managers;
}
