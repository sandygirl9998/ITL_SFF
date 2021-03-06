package com.lti.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lti.entity.Bidder;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.entity.User;

public interface AdminRepo {
	List<User> fStatusInQueue();
	List<User> fList();
	List<User> bStatusInQueue();
	List<User> bList();
	List<Crop> sellRequestInQueue();
	void updateCropSellRequest(int userId, int cropId,String cropSoldStatus, String adminApproval);
	void finalizeAuction(int cropId);
	void updateClaimRequest(int claimId,String claimStatus);
	List<Insurance> viewAllPolicy();
	public Bidder viewBidderProfile(int id, HttpServletRequest request);
	public ClaimInsurance viewClaimDoc(int id, HttpServletRequest request);
	public Farmer viewFarmerProfile(int id, HttpServletRequest request);
	

}
