package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.exception.UserServiceException;
import com.lti.repository.UserRepository;
import com.lti.service.FarmerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FarmerController {

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/fregister")
	public @ResponseBody Status register(@RequestBody Farmer farmer) {
		try {
			Status s = new Status();
			farmerService.add(farmer);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Registration Successful!");
			return s;
		} catch (UserServiceException e) {
			Status s = new Status();
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
	}

	@PostMapping("/apply")
	public @ResponseBody Status apply(@RequestParam("fid") int fid, @RequestBody Insurance insurance) {

		try {
			Status s = new Status();
			farmerService.insure(fid, insurance);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Insurance applied successfully");
			return s;
		} catch (UserServiceException e) {
			Status s = new Status();
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}

	}

	@PostMapping("/farmer-doc")
	public @ResponseBody Status upload( Document document,HttpServletRequest request) {
		
			Status status=farmerService.uploadDocs(document,request)	;
		return status;
	}
	
	
	
	
	// Placing Sell Request
	@PostMapping(value = "/addCrop")
	public @ResponseBody Status addCrop(@RequestParam("farmerId") int farmerId,@RequestBody Crop crop) {
		try {
			Status s = new Status();
			farmerService.addCrop(farmerId,crop);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Sell request placed successfully");
			return s;
		} catch (UserServiceException e) {
			Status s = new Status();
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
	}

	@GetMapping(value = "/listpolicies", produces = "application/json")
	public List<Insurance> getInsurances(@RequestParam("fid") int farmerid) {
		System.out.println("hello");
		return farmerService.policies(farmerid);
	}
	
}
