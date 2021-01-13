package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Insurance;
import com.lti.repository.InsuranceRepo;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {


	@Autowired
	private InsuranceRepo repo;
	
	@Override
	public void Apply(Insurance ins) {
		
		repo.Save(ins);
	}

	@Override
	public Insurance search(int polid) {
		
		return repo.fetch(polid);
	}

	
	
	@Override
	public void action(String status, int polid) {
		
		repo.update(status, polid);
	}

	}


