package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;
import com.lti.exception.UserServiceException;
import com.lti.repository.InsuranceRepo;
import com.lti.service.InsuranceService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	@Autowired
	private InsuranceRepo insuranceRepo;
	
	
	@GetMapping("/insurancestatus")
	public String updateInsurance(@RequestParam String status, @RequestParam int polid) {
		insuranceService.action(status, polid);
		return "Status changed successfully to " + status;
	}

	@GetMapping("/fetchpolicy")
	public @ResponseBody Insurance FetchPolicy(@RequestParam int id) {
		return insuranceService.search(id);
	}

	@PostMapping(value="/claimPolicy")
	public @ResponseBody Status addCrop(@RequestParam("policyId") int policyId, @RequestBody ClaimInsurance claim) {
		try {
			Status s=new Status();
			insuranceService.claim(policyId, claim);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Claim requested successfully");
			return s;
		}
		catch(UserServiceException e) {
			Status s=new Status();
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
		
	}
	@GetMapping(value ="/getunapproved", produces="application/json")
	public @ResponseBody List<ClaimInsurance> claims(){
		return insuranceRepo.getClaims();
	}
	
}
