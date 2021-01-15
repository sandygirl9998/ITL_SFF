package com.lti.repository;

import java.util.List;

import com.lti.entity.Crop;

import com.lti.entity.Farmer;
import com.lti.entity.Insurance;

public interface FarmerRepo {
	void save(Farmer farmer);	
	void deleteFarmer(int farmerId);
	void updateFarmer(Farmer farmer);
	void addInsurance(int farmerId, Insurance insurance);

	void sellRequest(int farmerId, Crop crop);

	List<Insurance> getinsurance(int farmerid);
}
