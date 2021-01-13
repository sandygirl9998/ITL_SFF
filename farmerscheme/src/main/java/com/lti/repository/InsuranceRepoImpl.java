package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

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

}
