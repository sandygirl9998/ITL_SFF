package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ClaimInsurance")
@SequenceGenerator(name="claimInSeq", sequenceName = "claimInSeq", initialValue = 1001, allocationSize = 1)

public class ClaimInsurance  {
	@Id
	@GeneratedValue(generator = "claimInSeq",strategy = GenerationType.SEQUENCE)
	private int claimId;
	
	
	
	@Column
	private String policyCompany;
	@Column
	private String InsureeName;
	@Column
	private String sumInsured;
	@Column
	private String claimReason;
	@Column
	private LocalDate dateOfLoss;
	@Column
	private String status="Queued";
	@Column 
	private String document;
	/*
	 * @OneToOne(mappedBy="claim") private Insurance insurance;
	 */
	
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	/*
	 * public Insurance getInsurance() { return insurance; } public void
	 * setInsurance(Insurance insurance) { this.insurance = insurance; }
	 */
	public String getPolicyCompany() {
		return policyCompany;
	}
	public void setPolicyCompany(String insuranceCompany) {
		this.policyCompany = insuranceCompany;
	}
	public String getInsureeName() {
		return InsureeName;
	}
	public void setInsureeName(String insureeName) {
		InsureeName = insureeName;
	}
	public String getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getClaimReason() {
		return claimReason;
	}
	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}
	
	public LocalDate getDateOfLoss() {
		return dateOfLoss;
	}
	public void setDateOfLoss(LocalDate dateOfLoss) {
		this.dateOfLoss = dateOfLoss;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
		
		
	
}
