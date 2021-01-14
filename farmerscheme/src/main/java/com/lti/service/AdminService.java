package com.lti.service;

import java.util.List;

import com.lti.entity.Crop;
import com.lti.entity.User;

public interface AdminService {
	List<User> fInQueue();
	List<User> fList();
	List<User> bInQueue();
	List<User> bList();
	List<Crop> sellRequestInQueue();
	public void updateCropSellRequest(int userId, int cropId,String cropSoldStatus, String adminApproval);
	void finalizeAuction(int cropId);

}
