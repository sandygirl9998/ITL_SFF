package com.lti.repository;

import com.lti.entity.Bidder;

public interface BidderRepo {
	void save(Bidder bidder);
	void deleteBidder(int bidderId);
	void updateBidder(Bidder bidder);

}
