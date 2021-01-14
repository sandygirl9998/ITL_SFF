package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Bidder;
import com.lti.entity.Bids;
import com.lti.entity.Crop;

@Repository
@Transactional
public class BidderRepoImpl implements BidderRepo {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Bidder bidder) {
		bidder.setRole("Bidder");
		em.persist(bidder);
	}

	
	@Override
	public void deleteBidder(int bidderId) {
		em.remove(em.find(Bidder.class, bidderId));

	}

	@Override
	public void updateBidder(Bidder bidder) {
		em.merge(bidder);

	}
	
	@Override
    public void makeBid(int bidderid, int cropid, Bids bid) {
        Bidder bidder=em.find(Bidder.class, bidderid);
        bidder.getBids().add(bid);
        Crop c=em.find(Crop.class,cropid);
        c.getBids().add(bid);
        bid.setCrop(c);
        bid.setBidder(bidder);
        em.persist(bid);
        em.merge(bidder);
       
       
    }

}
