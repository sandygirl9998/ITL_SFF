package com.lti.service;

import java.util.List;

import com.lti.entity.Bidder;
import com.lti.entity.Bids;

public interface BidderService {
	void add(Bidder bidder);
	public void updateAadhar(String emailId, String newFileName) ;
	public void updatePAN(String emailId,String newFileName);
	public void updateLicense(String emailId,String newFileName);
	void placeBid(int bidderid, int cropid, Bids bid);
	List<Bids> viewOwnBids(int bidderid);
}
