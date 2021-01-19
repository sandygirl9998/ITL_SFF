package com.lti.service;

import javax.servlet.http.HttpServletRequest;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;

public interface InsuranceService {

	void Apply(Insurance ins);

	Insurance search(int polid);

	void action(String status, int polid);

	 void claim(int policyId, ClaimInsurance claim);

	void updateClaimDocument(int policyId, String newFileName);

	Status uploadDocs(int policyId, Document document, HttpServletRequest request);
}
