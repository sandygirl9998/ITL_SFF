package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;

@Repository
@Transactional
public class InsuranceRepoImpl implements InsuranceRepo {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void Save(Insurance ins) {

		em.persist(ins);
	}

	@Override
	public Insurance fetch(int polid) {

		return em.find(Insurance.class, polid);
	}

	@Override
	public void update(String status, int polid) {

		Insurance i = em.find(Insurance.class, polid);
		i.setPolicyStatus(status);
		em.merge(i);
	}
	@Override
	public void claimPolicy(int policyId,ClaimInsurance claim) {
		Insurance insurance =em.find(Insurance.class,policyId);
		insurance.setClaim(claim);
		em.persist(claim);		
		
		em.merge(insurance);
	}
	
	public boolean isClaimPresent(int policyId) {
		System.out.println(policyId);
		boolean b=(Long)
				em
				.createQuery("select count(c.claimId) from ClaimInsurance c where c.claimId = :id")
				.setParameter("id", policyId)
				.getSingleResult() == 0 ? true : false;
		System.out.println(b);
		return b;
	}
	
	public List<ClaimInsurance> getClaims() {
		return em.createQuery("FROM ClaimInsurance where status='Queued'").getResultList();
	}
}
