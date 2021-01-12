package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.User;

@Repository
public class AdminRepoImpl implements AdminRepo {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> fStatusInQueue() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("From User where status='Queued' AND role='Farmer'");
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public List<User> fList() {
		Query q = em.createQuery("From User where status='Approved' AND role='Farmer'");
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public List<User> bStatusInQueue() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("From User where status='Queued' AND role='Bidder'");
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public List<User> bList() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("From User where status='Approved' AND role='Bidder'");
		List<User> users = q.getResultList();
		return users;
	}

}
