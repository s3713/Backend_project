package com.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Users {

	public long getUser_id() {
		return user_id;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(long user_id, @Size(min = 0, max = 50) String email, @Size(min = 0, max = 50) String password,
			@Size(min = 0, max = 50) String first_name, @Size(min = 0, max = 50) String last_name,
			@Size(min = 0, max = 15) String phone, @Size(min = 0, max = 200) String address,
			@Size(min = 0, max = 10) String pin, SecurityQuestion securityQuestion,
			@Size(min = 0, max = 100) String securityAnswer, @Size(min = 0, max = 10) String pan, LocalDate dob,
			long annualIncome, RoleEnum role, LocalDateTime createdAt, LocalDateTime updatedAt,
			Set<LoanApplication> loanApplications) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.address = address;
		this.pin = pin;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.pan = pan;
		this.dob = dob;
		this.annualIncome = annualIncome;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.loanApplications = loanApplications;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public SecurityQuestion getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(SecurityQuestion securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public long getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(long annualIncome) {
		this.annualIncome = annualIncome;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<LoanApplication> getLoanApplications() {
		return loanApplications;
	}

	public void setLoanApplications(Set<LoanApplication> loanApplications) {
		this.loanApplications = loanApplications;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;

	// @NotNull
	@Size(min = 0, max = 50)
	private String email;

	// @NotNull
	@Size(min = 0, max = 50)
	private String password;

	// @NotNull
	@Size(min = 0, max = 50)
	private String first_name;

	// @NotNull
	@Size(min = 0, max = 50)
	private String last_name;

	// @NotNull
	@Size(min = 0, max = 15)
	private String phone;

	// @NotNull
	@Size(min = 0, max = 200)
	private String address;

	// @NotNull
	@Size(min = 0, max = 10)
	private String pin;

	// @NotNull
	@Enumerated(EnumType.STRING)
	private SecurityQuestion securityQuestion;

	// @NotNull
	@Size(min = 0, max = 100)
	private String securityAnswer;

	// @NotNull
	@Size(min = 0, max = 10)
	private String pan;

	// @NotNull
	private LocalDate dob;

	// @NotNull
	private long annualIncome;

	// @NotNull
	@Enumerated(EnumType.STRING)
	private RoleEnum role;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "user")
	private Set<LoanApplication> loanApplications; // New field

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public enum SecurityQuestion {
		FIRST_SCHOOL, GRANDMOTHER_NAME, FAVOURITE_FOOD
	}

	public enum RoleEnum {
		CUSTOMER, ADMIN, LOAN_OFFICER
	}
}
