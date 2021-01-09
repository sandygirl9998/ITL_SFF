package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Bidder")
@PrimaryKeyJoinColumn(name = "bidderid", referencedColumnName = "user_id")
@SecondaryTables({ @SecondaryTable(name = "bidder_doc", pkJoinColumns = @PrimaryKeyJoinColumn(name = "bidderid")),
		@SecondaryTable(name = "bidder_info", pkJoinColumns = @PrimaryKeyJoinColumn(name = "bidderid")) })

public class Bidder extends User {
	@Column(length = 10, table = "bidder_info")
	private String bidderContact;

	@Column(length = 50, table = "bidder_info")
	private String bidderAddressLine1;

	@Column(length = 30, table = "bidder_info")
	private String bidderAddressLine2;

	@Column(length = 25, table = "bidder_info")
	private String bidderCity;

	@Column(length = 25, table = "bidder_info")
	private String bidderState;

	@Column(length = 7, table = "bidder_info")
	private int bidderPINCODE;

	@Column(table = "bidder_info")
	private String bidderAccountNumber;

	@Column(length = 25, table = "bidder_info")
	private String bidderIFSC;

	@Column(length = 100, table = "bidder_doc")
	private String bidderPAN;

	@Column(length = 100, table = "bidder_doc")
	private String bidderAADHAR;

	@Column(length = 100, table = "bidder_doc")
	private String bidderLicense;

	public Bidder() {
		// TODO Auto-generated constructor stub
	}

	public Bidder(String bidderContact, String bidderAddressLine1, String bidderAddressLine2, String bidderCity,
			String bidderState, int bidderPINCODE, String bidderAccountNumber, String bidderIFSC, String bidderPAN,
			String bidderAADHAR, String bidderLicense) {
		this.bidderContact = bidderContact;
		this.bidderAddressLine1 = bidderAddressLine1;
		this.bidderAddressLine2 = bidderAddressLine2;
		this.bidderCity = bidderCity;
		this.bidderState = bidderState;
		this.bidderPINCODE = bidderPINCODE;
		this.bidderAccountNumber = bidderAccountNumber;
		this.bidderIFSC = bidderIFSC;
		this.bidderPAN = bidderPAN;
		this.bidderAADHAR = bidderAADHAR;
		this.bidderLicense = bidderLicense;
	}

	public String getBidderContact() {
		return bidderContact;
	}

	public void setBidderContact(String bidderContact) {
		this.bidderContact = bidderContact;
	}

	public String getBidderAddressLine1() {
		return bidderAddressLine1;
	}

	public void setBidderAddressLine1(String bidderAddressLine1) {
		this.bidderAddressLine1 = bidderAddressLine1;
	}

	public String getBidderAddressLine2() {
		return bidderAddressLine2;
	}

	public void setBidderAddressLine2(String bidderAddressLine2) {
		this.bidderAddressLine2 = bidderAddressLine2;
	}

	public String getBidderCity() {
		return bidderCity;
	}

	public void setBidderCity(String bidderCity) {
		this.bidderCity = bidderCity;
	}

	public String getBidderState() {
		return bidderState;
	}

	public void setBidderState(String bidderState) {
		this.bidderState = bidderState;
	}

	public int getBidderPINCODE() {
		return bidderPINCODE;
	}

	public void setBidderPINCODE(int bidderPINCODE) {
		this.bidderPINCODE = bidderPINCODE;
	}

	public String getBidderAccountNumber() {
		return bidderAccountNumber;
	}

	public void setBidderAccountNumber(String bidderAccountNumber) {
		this.bidderAccountNumber = bidderAccountNumber;
	}

	public String getBidderIFSC() {
		return bidderIFSC;
	}

	public void setBidderIFSC(String bidderIFSC) {
		this.bidderIFSC = bidderIFSC;
	}

	public String getBidderPAN() {
		return bidderPAN;
	}

	public void setBidderPAN(String bidderPAN) {
		this.bidderPAN = bidderPAN;
	}

	public String getBidderAADHAR() {
		return bidderAADHAR;
	}

	public void setBidderAADHAR(String bidderAADHAR) {
		this.bidderAADHAR = bidderAADHAR;
	}

	public String getBidderLicense() {
		return bidderLicense;
	}

	public void setBidderLicense(String bidderLicense) {
		this.bidderLicense = bidderLicense;
	}

}
