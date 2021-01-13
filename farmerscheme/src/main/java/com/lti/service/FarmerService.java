package com.lti.service;

import com.lti.entity.Farmer;

public interface FarmerService {
	void add(Farmer farmer);
	public void updateAadhar(String emailId, String newFileName) ;
	public void updatePAN(String emailId,String newFileName);
	public void updateCertificate(String emailId,String newFileName);

}
