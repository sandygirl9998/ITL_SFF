package com.lti.repository;

import com.lti.entity.Farmer;

public interface FarmerRepo {
	void save(Farmer farmer);	
	void deleteFarmer(int farmerId);
	void updateFarmer(Farmer farmer);

}
