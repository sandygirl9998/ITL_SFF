package com.lti.service;

import java.util.List;

import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.repository.Policies;

public interface FarmerService {
	void add(Farmer farmer);

	void insure(int fid, Insurance insurance);

	public void updateAadhar(String emailId, String newFileName) ;
	public void updatePAN(String emailId,String newFileName);
	public void updateCertificate(String emailId,String newFileName);

	void addCrop(int farmerId, Crop crop);
	List<Policies> policies(int farmerid);


}
