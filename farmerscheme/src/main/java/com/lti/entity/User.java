package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "userSeq", sequenceName = "userSeq", initialValue = 101, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
	@Column(name = "user_id")
	private int userId;
	@Column(length = 50, unique = true)
	private String emailId;
	@Column(length = 100)
	private String password;
	@Column(length = 10)
	private String role;
	@Column(length = 30)
	private String name;
	@Column(length = 30)
	private String status = "Queued";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}