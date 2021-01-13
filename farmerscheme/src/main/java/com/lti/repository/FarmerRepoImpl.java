package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Farmer;
import com.lti.entity.Insurance;

@Repository
@Transactional
public class FarmerRepoImpl implements FarmerRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Farmer farmer) {
		em.persist(farmer);
	}


	@Override
	public void deleteFarmer(int farmerId) {
		em.remove(em.find(Farmer.class, farmerId));
	}

	@Override
	public void updateFarmer(Farmer farmer) {
		em.merge(farmer);

	}


	@Override
	public void addInsurance(int farmerId, Insurance insurance) {
		Farmer farmer=em.find(Farmer.class,farmerId);
		System.out.println("hello");
		farmer.getInsurance().add(insurance);
		System.out.println("hello");
		em.persist(insurance);
		System.out.println("hello");
		em.merge(farmer);
		
	}

}
