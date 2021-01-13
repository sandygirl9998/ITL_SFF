package com.lti.service;

import com.lti.entity.Farmer;
import com.lti.entity.Insurance;

public interface FarmerService {
	void add(Farmer farmer);

	void insure(int fid, Insurance insurance);

	public void updateAadhar(String emailId, String newFileName) ;
	public void updatePAN(String emailId,String newFileName);
	public void updateCertificate(String emailId,String newFileName);


}
