package com.lti.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Farmer")
@PrimaryKeyJoinColumn(name = "farmerid", referencedColumnName = "user_id")
@SecondaryTables({ @SecondaryTable(name = "farmer_info"), @SecondaryTable(name = "farmer_doc") })

public class Farmer extends User {

	@Column(length = 50, table = "farmer_info")
	private String farmerAddressLine1;
	@Column(length = 50, table = "farmer_info")
	private String farmerAddressLine2;
	@Column(length = 20, table = "farmer_info")
	private String farmerCity;
	@Column(length = 15, table = "farmer_info")
	private String farmerState;
	@Column(length = 100, table = "farmer_doc")
	private String farmerPAN;
	@Column(length = 100, table = "farmer_doc")
	private String farmerCertificate;
	@Column(table = "farmer_info")
	private int farmerPINCode;
	@Column(length = 100, table = "farmer_doc")
	private String farmerAADHAR;
	@Column(length = 15, table = "farmer_info")
	private String farmerIFSC;
	@Column(table = "farmer_info")
	private long farmerAccountNumber;

	@Column(length = 50, table = "farmer_info")
	private String farmerLandAddress;
	@Column(length = 6, table = "farmer_info")
	private String farmerLandPIN;
	@Column(length = 20, table = "farmer_info")
	private String farmerLandArea;
	@Column(length = 10)
	private String farmerContact;
	
	
	@OneToMany(cascade = { CascadeType.ALL })

	@JsonIgnore

	
	private List<Insurance> insurance = new ArrayList<Insurance>();

	public String getFarmerContact() {
		return farmerContact;
	}

	public void setFarmerContact(String farmerContact) {
		this.farmerContact = farmerContact;
	}

	

	public String getFarmerCertificate() {
		return farmerCertificate;
	}

	public void setFarmerCertificate(String farmerCertificate) {
		this.farmerCertificate = farmerCertificate;
	}


	public String getFarmerAddressLine1() {
		return farmerAddressLine1;
	}

	public void setFarmerAddressLine1(String farmerAddressLine1) {
		this.farmerAddressLine1 = farmerAddressLine1;
	}

	public String getFarmerAddressLine2() {
		return farmerAddressLine2;
	}

	public void setFarmerAddressLine2(String farmerAddressLine2) {
		this.farmerAddressLine2 = farmerAddressLine2;
	}

	public String getFarmerCity() {
		return farmerCity;
	}

	public void setFarmerCity(String farmerCity) {
		this.farmerCity = farmerCity;
	}

	public String getFarmerState() {
		return farmerState;
	}

	public void setFarmerState(String farmerState) {
		this.farmerState = farmerState;
	}

	public String getFarmerPAN() {
		return farmerPAN;
	}

	public void setFarmerPAN(String farmerPAN) {
		this.farmerPAN = farmerPAN;
	}

	public int getFarmerPINCode() {
		return farmerPINCode;
	}

	public void setFarmerPINCode(int farmerPINCode) {
		this.farmerPINCode = farmerPINCode;
	}

	public String getFarmerAADHAR() {
		return farmerAADHAR;
	}

	public void setFarmerAADHAR(String farmerAADHAR) {
		this.farmerAADHAR = farmerAADHAR;
	}

	public String getFarmerIFSC() {
		return farmerIFSC;
	}

	public void setFarmerIFSC(String farmerIFSC) {
		this.farmerIFSC = farmerIFSC;
	}

	public long getFarmerAccountNumber() {
		return farmerAccountNumber;
	}

	public void setFarmerAccountNumber(long farmerAccountNumber) {
		this.farmerAccountNumber = farmerAccountNumber;
	}


	public String getFarmerLandAddress() {
		return farmerLandAddress;
	}

	public void setFarmerLandAddress(String farmerLandAddress) {
		this.farmerLandAddress = farmerLandAddress;
	}

	public String getFarmerLandPIN() {
		return farmerLandPIN;
	}

	public void setFarmerLandPIN(String farmerLandPIN) {
		this.farmerLandPIN = farmerLandPIN;
	}

	public String getFarmerLandArea() {
		return farmerLandArea;
	}

	public void setFarmerLandArea(String farmerLandArea) {
		this.farmerLandArea = farmerLandArea;
	}

	public List<Insurance> getInsurance() {
		return insurance;
	}

	public void setInsurance(List<Insurance> insurance) {
		this.insurance = insurance;
	}


}