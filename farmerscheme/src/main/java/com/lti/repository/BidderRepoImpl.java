package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Bidder;

@Repository
@Transactional
public class BidderRepoImpl implements BidderRepo {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Bidder bidder) {
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

}
