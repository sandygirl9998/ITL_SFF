package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Farmer;
import com.lti.exception.UserServiceException;
import com.lti.service.FarmerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FarmerController {
	
	@Autowired
	private FarmerService farmerService;
	
	@PostMapping("/fregister")
	public @ResponseBody Status register(@RequestBody Farmer farmer) {
		try {
			Status s= new Status();
			farmerService.add(farmer);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Registration Successful!");
			return s;
		}
		catch(UserServiceException e) {
			Status s= new Status();			
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
	}
}
