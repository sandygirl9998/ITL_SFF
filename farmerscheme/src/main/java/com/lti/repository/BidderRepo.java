package com.lti.repository;

import com.lti.entity.Bidder;
import com.lti.entity.Bids;

public interface BidderRepo {
	void save(Bidder bidder);
	void deleteBidder(int bidderId);
	void updateBidder(Bidder bidder);
	void makeBid(int bidderid, int cropid, Bids bid);

}
