package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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
	public List<Policies> getinsurance(int farmerid) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Query q= em.createNativeQuery("select policy_Id, policy_Company,policy_Crop_Area,season,policy_Shared_Premium, policy_Status from Insurance where policy_Id in" + 
				"(select insurance_policy_id from farmer_insurance where farmer_farmerid=:fid)");
		q.setParameter("fid",farmerid);
		System.out.println(q);
		List<Object[]> policies=q.getResultList();
		List<Policies> insurance=new ArrayList<Policies>();
		for(Object[] c: policies)
		{ Policies p= new Policies();
			p.setPolicyId((Number)c[0]);
			p.setPolicyCompany((String)c[1]);
			p.setPolicyCropArea((Number)c[2]);
			p.setSeason((String)c[3]);
			p.setPolicySharedPremium((Number)c[4]);
			p.setStatus((String)c[5]);
			insurance.add(p);
		}
		System.out.println("hello");
		return insurance;
	}

}
