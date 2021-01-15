package com.lti.repository;

import java.util.List;

import com.lti.entity.Crop;
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
	void updateClaimRequest(int claimId);
	List<Insurance> viewAllPolicy();

}
