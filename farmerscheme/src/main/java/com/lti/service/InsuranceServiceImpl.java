package com.lti.service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;
import com.lti.repository.InsuranceRepo;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {


	@Autowired
	private InsuranceRepo insuranceRepo;
	@Autowired	
	private UserRepository userRepository;
	
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
		insuranceRepo.claimPolicy(policyId, claim);	
		}
	@Override
	public void updateClaimDocument(int policyId, String newFileName) {
		Insurance insurance = insuranceRepo.fetch(policyId);
		int id=insurance.getClaim().getClaimId();
		ClaimInsurance claim=userRepository.fetch(ClaimInsurance.class,id);
		claim.setDocument(newFileName);
		insuranceRepo.updateClaim(claim);
	}
	
	@Override
	public Status uploadDocs(int policyId, Document document, HttpServletRequest request) {
		Status status=insuranceRepo.uploadDocs(policyId, document, request);
		return status;
	}
	
	}


