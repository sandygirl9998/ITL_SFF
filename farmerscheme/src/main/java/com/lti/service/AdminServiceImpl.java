package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.User;
import com.lti.repository.AdminRepo;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepo repo;

	@Override
	public List<User> fInQueue() {
		// TODO Auto-generated method stub
		return repo.fStatusInQueue();
	}

	@Override
	public List<User> fList() {
		// TODO Auto-generated method stub
		return repo.fList();
	}

	@Override
	public List<User> bInQueue() {
		// TODO Auto-generated method stub
		return repo.bStatusInQueue();
	}

	@Override
	public List<User> bList() {
		// TODO Auto-generated method stub
		return repo.bList();
	}

}
