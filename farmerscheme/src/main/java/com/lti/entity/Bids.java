package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Bids")
@SequenceGenerator(name = "bidSeq", sequenceName = "bidSeq", initialValue = 101, allocationSize = 1)
public class Bids {

	@Id
	@GeneratedValue(generator = "bidSeq", strategy = GenerationType.SEQUENCE)
	private int bidId;

	@Column
	private int bidAmount;

	@ManyToOne
	@JoinColumn(name="bidderid")
	private Bidder bidder;

	@ManyToOne
	private Crop crop;

	private String status="Pending";

//	public Bids(int bidId, int bidAmount, Bidder bidder, Crop crop, String status) {
//		this.bidId = bidId;
//		this.bidAmount = bidAmount;
//		this.bidder = bidder;
//		this.crop = crop;
//		this.status = status;
//	}

	public Bids() {
		// TODO Auto-generated constructor stub
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
