package com.lti.dto;


import org.springframework.web.multipart.MultipartFile;

public class Document {
	
	private String emailId;
	private MultipartFile Aadhar;
	private MultipartFile PAN;
	private MultipartFile Certificate;
	private MultipartFile License;
	private MultipartFile claimDocument;
	
	public MultipartFile getClaimDocument() {
		return claimDocument;
	}
	public void setClaimDocument(MultipartFile claimDocument) {
		this.claimDocument = claimDocument;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public MultipartFile getAadhar() {
		return Aadhar;
	}
	public void setAadhar(MultipartFile aadhar) {
		Aadhar = aadhar;
	}
	public MultipartFile getPAN() {
		return PAN;
	}
	public void setPAN(MultipartFile pAN) {
		PAN = pAN;
	}
	public MultipartFile getCertificate() {
		return Certificate;
	}
	public void setCertificate(MultipartFile certificate) {
		Certificate = certificate;
	}
	public MultipartFile getLicense() {
		return License;
	}
	public void setLicense(MultipartFile license) {
		License = license;
	}
	
	
	
	
	
}

