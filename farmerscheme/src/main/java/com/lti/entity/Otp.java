package com.lti.entity;

import java.time.LocalDateTime;

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
@Table(name = "tbl_otp")

@SequenceGenerator(name = "otpSeq", sequenceName = "otpSeq", initialValue = 1000, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)

public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otpSeq")
	private int otpId;

	private LocalDateTime timeOfGeneration;
	private String otp;
	@Column(unique = true)
	private int user_id;

	public int getOtpId() {
		return otpId;
	}

	public void setOtpId(int otpId) {
		this.otpId = otpId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getTimeOfGeneration() {
		return timeOfGeneration;
	}

	public void setTimeOfGeneration(LocalDateTime localDateTime) {
		this.timeOfGeneration = localDateTime;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
