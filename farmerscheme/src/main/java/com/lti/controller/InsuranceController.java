package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lti.entity.Insurance;
import com.lti.service.InsuranceService;

@Controller
public class InsuranceController {

	@Autowired
	private InsuranceService service;

	
	
	@GetMapping("/insurancestatus")
	public String updateInsurance(@RequestParam String status, @RequestParam int polid) {
		service.action(status, polid);
		return "Status changed successfully to " + status;
	}

	@GetMapping("/fetchpolicy")
	public Insurance FetchPolicy(@RequestParam int id) {
		return service.search(id);
	}

	
	
}
