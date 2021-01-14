package com.lti.repository;

public class Policies {
	private Number policyId;
	private String policyCompany;
	private Number policyCropArea;
	private String season;
	private Number policySharedPremium;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Number getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Number policyId) {
		this.policyId = policyId;
	}
	public String getPolicyCompany() {
		return policyCompany;
	}
	public void setPolicyCompany(String policyCompany) {
		this.policyCompany = policyCompany;
	}
	public Number getPolicyCropArea() {
		return policyCropArea;
	}
	public void setPolicyCropArea(Number policyCropArea) {
		this.policyCropArea = policyCropArea;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public Number getPolicySharedPremium() {
		return policySharedPremium;
	}
	public void setPolicySharedPremium(Number policySharedPremium) {
		this.policySharedPremium = policySharedPremium;
	}

}
