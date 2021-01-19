package com.lti.repository;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;


public interface InsuranceRepo {
	
	void Save(Insurance ins);
	Insurance fetch(int polid);
   void update(String status, int polid);
   void claimPolicy(int policyId, ClaimInsurance claim);
	boolean isClaimPresent(int policyId);
	List<ClaimInsurance> getClaims();
	void updateClaim(ClaimInsurance claim);
	Status uploadDocs(int policyId, Document document, HttpServletRequest request);
	

}
