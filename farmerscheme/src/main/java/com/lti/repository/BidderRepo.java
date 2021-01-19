package com.lti.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.entity.Bidder;
import com.lti.entity.Bids;

public interface BidderRepo {
	void save(Bidder bidder);
	void deleteBidder(int bidderId);
	void updateBidder(Bidder bidder);
	void makeBid(int bidderid, int cropid, Bids bid);
	List<Bids> viewOwnBids(int bidderid);
	Status uploadDocs(Document document, HttpServletRequest request);

}
