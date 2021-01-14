package com.lti.repository;
import java.util.List;

import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;


public interface InsuranceRepo {
	
	void Save(Insurance ins);
	Insurance fetch(int polid);
   void update(String status, int polid);
   void claimPolicy(int policyId, ClaimInsurance claim);
	boolean isClaimPresent(int policyId);
	List<ClaimInsurance> getClaims();
	

}
