package com.lti.service;

import com.lti.entity.Bidder;

public interface BidderService {
	void add(Bidder bidder);
	public void updateAadhar(String emailId, String newFileName) ;
	public void updatePAN(String emailId,String newFileName);
	public void updateLicense(String emailId,String newFileName);
}
