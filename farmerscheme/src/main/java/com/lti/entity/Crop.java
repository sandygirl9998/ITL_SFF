package com.lti.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Crop")
@SequenceGenerator(name = "cropSeq", sequenceName = "crop_seq12", initialValue = 801, allocationSize = 1)
public class Crop {
	@Id
	@GeneratedValue(generator = "cropSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "cropId")
	private int cropId;
	@Column(length = 30)
	private String cropName;
	@Column(length = 30)
	private String cropType;
	@Column(length = 30)
	private String fertilizer;
	@Column
	private double cropBasePrice;
	@Column
	private double cropSoldPrice=0.0;

	@Column
	private Date cropSoldDate;
//	@Column(length = 14)
//	private String cropSoldDate="NA";
	@Column
	private double currentBid=0;
	@Column
	private int cropQuantity;
	@Column(length = 80)
	private String cropSoldStatus = "Queued";
	@Column(length = 50)
	private String soilPH;
	@Column
	private String adminApproval = "Pending";
	
	@ManyToOne
	@JoinColumn(name="farmerid")
	private Farmer farmer;
	
	

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "bidder_crop", joinColumns = { @JoinColumn(name = "cropId") }, inverseJoinColumns = {
			@JoinColumn(name = "bidderid") })
	@JsonIgnore
	private List<Bidder> bidder = new ArrayList<Bidder>();

//	@OneToMany(mappedBy="bidId",cascade = { CascadeType.ALL })
	@OneToMany(cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Bids> bids = new ArrayList<Bids>();



	public List<Bids> getBids() {
		return bids;
	}

	public void setBids(List<Bids> bids) {
		this.bids = bids;
	}



	public List<Bidder> getBidder() {
		return bidder;
	}

	public void setBidder(List<Bidder> bidder) {
		this.bidder = bidder;
	}

	public int getCropId() {
		return cropId;
	}

	public void setCropId(int cropId) {
		this.cropId = cropId;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public String getFertilizer() {
		return fertilizer;
	}

	public void setFertilizer(String fertilizer) {
		this.fertilizer = fertilizer;
	}

	public double getCropBasePrice() {
		return cropBasePrice;
	}

	public void setCropBasePrice(double cropBasePrice) {
		this.cropBasePrice = cropBasePrice;
	}

	public double getCropSoldPrice() {
		return cropSoldPrice;
	}

	public void setCropSoldPrice(double cropSoldPrice) {
		this.cropSoldPrice = cropSoldPrice;
	}

	public Date getCropSoldDate() {
		return cropSoldDate;
	}

	public void setCropSoldDate(Date cropSoldDate) {
		this.cropSoldDate = cropSoldDate;
	}

	public int getCropQuantity() {
		return cropQuantity;
	}

	public void setCropQuantity(int cropQuantity) {
		this.cropQuantity = cropQuantity;
	}

	public String getCropSoldStatus() {
		return cropSoldStatus;
	}

	public void setCropSoldStatus(String cropSoldStatus) {
		this.cropSoldStatus = cropSoldStatus;
	}

	public String getSoilPH() {
		return soilPH;
	}

	public void setSoilPH(String soilPH) {
		this.soilPH = soilPH;
	}

	public Number getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}

	public String getAdminApproval() {
		return adminApproval;
	}

	public void setAdminApproval(String adminApproval) {
		this.adminApproval = adminApproval;
	}

	@Override
	public String toString() {
		return "Crop [cropId=" + cropId + ", cropName=" + cropName + ", cropType=" + cropType + ", fertilizer="
				+ fertilizer + ", cropBasePrice=" + cropBasePrice + ", cropSoldPrice=" + cropSoldPrice
				+ ", cropSoldDate=" + cropSoldDate + ", currentBid=" + currentBid + ", cropQuantity=" + cropQuantity
				+ ", cropSoldStatus=" + cropSoldStatus + ", soilPH=" + soilPH + ", bidder=" + bidder + ", bids=" + bids
				+ "]";
	}

}
