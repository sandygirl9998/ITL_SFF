package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Bids;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;

@Repository
@Transactional
public class FarmerRepoImpl implements FarmerRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Farmer farmer) {
		farmer.setRole("Farmer");
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
		Farmer farmer = em.find(Farmer.class, farmerId);
		System.out.println("hello");
		farmer.getInsurance().add(insurance);
		System.out.println("hello");
		insurance.setFarmer(farmer);
		em.persist(insurance);
		System.out.println("hello");
		em.merge(farmer);

	}

	@Override

	public void sellRequest(int farmerId, Crop crop) {

		Farmer farmer = em.find(Farmer.class, farmerId);
		farmer.getCrops().add(crop);
		double d = crop.getCropBasePrice();
		crop.setCurrentBid(d);
		crop.setFarmer(farmer);
		em.persist(crop);
		em.merge(farmer);
	}
	
	@Override
	public List<Insurance> getinsurance(int farmerid) {

		Query q = em.createQuery("from Insurance where farmerid=:id");
		q.setParameter("id", farmerid);
		List<Insurance> lu = q.getResultList();
		return lu;
	}


}
