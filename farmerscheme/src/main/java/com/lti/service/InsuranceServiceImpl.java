package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;
import com.lti.exception.UserServiceException;
import com.lti.repository.InsuranceRepo;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {


	@Autowired
	private InsuranceRepo insuranceRepo;
	
	@Override
	public void Apply(Insurance ins) {
		
		insuranceRepo.Save(ins);
	}

	@Override
	public Insurance search(int polid) {
		
		return insuranceRepo.fetch(polid);
	}

	
	
	@Override
	public void action(String status, int polid) {
		
		insuranceRepo.update(status, polid);
	}
	
	public void claim(int policyId, ClaimInsurance claim) {
		
		
		try {
			if(!insuranceRepo.isClaimPresent(policyId))
				throw new UserServiceException("Already applied for Claim!");
			insuranceRepo.claimPolicy(policyId, claim);
		}
		//catch(EmptyResultDataAccessException e) {
		catch(UserServiceException e) {
			throw new UserServiceException("Already applied for Claim!");
		}
		
		}
	}


