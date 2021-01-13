package com.lti.entity;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Insurance")
@SequenceGenerator(name="insuranceSeq", sequenceName = "insurance_seq8", initialValue = 1001, allocationSize = 1)

public class Insurance {
	@Id
	@GeneratedValue(generator = "insuranceSeq",strategy = GenerationType.SEQUENCE)
	private int policyId;

	
	/*
	 * @OneToOne(mappedBy = "insurance",cascade = CascadeType.ALL)
	 * 
	 * private InsuranceClaim claim;
	 */
	
	@Column(length=10)
	private String season;
	@Column
	private double sumPerHectare;
	private int policyCropArea;
	@Column(length =50)
	private String policyStatus="Active";
	private double policySharedPremium=0.0;
	private double policyPremiumAmount=0.0;
	@Column(length=30)
	private String policyCompany="LIC";
	@Column(length=10)
	private String year;
	private double sumInsured;
	@Column(length=15)
	private String crop_name;
	
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getCrop_name() {
		return crop_name;
	}
	public void setCrop_name(String crop_name) {
		this.crop_name = crop_name;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getSeason() {
		return season;
	}
	
	public void setSeason(String season) {
		this.season = season;
	}
	public double getSumPerHectare() {
		return sumPerHectare;
	}
	public void setSumPerHectare(double sumPerHectare) {
		this.sumPerHectare = sumPerHectare;
	}
	public int getPolicyCropArea() {
		return policyCropArea;
	}
	public void setPolicyCropArea(int policyCropArea) {
		this.policyCropArea = policyCropArea;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public double getPolicySharedPremium() {
		return policySharedPremium;
	}
	public void setPolicySharedPremium(double policySharedPremium) {
		this.policySharedPremium = policySharedPremium;
	}
	public double getPolicyPremiumAmount() {
		return policyPremiumAmount;
	}
	public void setPolicyPremiumAmount(double policyPremiumAmount) {
		this.policyPremiumAmount = policyPremiumAmount;
	}
	public String getPolicyCompany() {
		return policyCompany;
	}
	public void setPolicyCompany(String policyCompany) {
		this.policyCompany = policyCompany;
	}
	
	
	/*
	 * public InsuranceClaim getClaim() { return claim; } public void
	 * setClaim(InsuranceClaim claim) { this.claim = claim; }
	 */
	


	
}
