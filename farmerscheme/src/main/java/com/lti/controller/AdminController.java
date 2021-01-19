package com.lti.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Bidder;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.entity.User;
import com.lti.service.AdminService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PersistenceContext
	EntityManager em;

	// fetch farmers whose status is queued
	@GetMapping(value = "/farmerapproval", produces = "application/json")
	public List<User> farmerInQueue() {
		return adminService.fInQueue();
	}

	// fetch farmers whose status is approved
	@GetMapping(value = "/farmerlist", produces = "application/json")
	public List<User> farmerList() {
		return adminService.fList();
	}

	// fetch bidders whose status is queued
	@GetMapping(value = "/bidderapproval", produces = "application/json")
	public List<User> bidderInQueue() {
		return adminService.bInQueue();
	}

	// fetch bidders whose status is approved
	@GetMapping(value = "/bidderlist", produces = "application/json")
	public List<User> bidderList() {
		return adminService.bList();
	}

	// fetch crops sell request placed by farmer
	@GetMapping(value = "/cropSellRequestList", produces = "application/json")
	public List<Crop> sellRequestInQueue() {
		return adminService.sellRequestInQueue();
	}

	// for crop sell request placed by farmer approval
	@PutMapping(value = "/approveCropRequest")
	public @ResponseBody Status updateCropSellRequest(@RequestParam("cropId") int cropId,
			@RequestParam("userId") int userId, @RequestParam("cropSoldStatus") String cropSoldStatus,
			@RequestParam("adminApproval") String adminApproval) {
		adminService.updateCropSellRequest(userId, cropId, cropSoldStatus, adminApproval);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Approval Done");
		return status;
	}

	// fetch crops in Market is already done as cropsforbidder

	// finalize bid
	@PutMapping(value = "/finalizeAuction")
	public @ResponseBody Status finalizeAuction(@RequestParam("cropId") int cropId) {
		adminService.finalizeAuction(cropId);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Approval Done");
		return status;
	}

	// Admin--Approve claim request
	@PutMapping(value = "/approveClaimRequest")
	public @ResponseBody Status updateClaimRequest(@RequestParam("claimId") int claimId,
			@RequestParam String claimStatus) {
		adminService.updateClaimRequest(claimId, claimStatus);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Approval Done");
		return status;
	}

	// fetch crops sell request placed by farmer
	@GetMapping(value = "/viewPolicy", produces = "application/json")
	public List<Insurance> viewAllPolicy() {
		return adminService.viewAllPolicy();
	}

	@GetMapping("/farmerProfile")
	public Farmer farmerProfile(@RequestParam int id, HttpServletRequest request) {

		adminService.viewFarmerProfile(id, request);
		return adminService.viewFarmerProfile(id, request);
	}

	@GetMapping("/bidderProfile")
	public Bidder bidderProfile(@RequestParam int id, HttpServletRequest request) {
		adminService.viewBidderProfile(id, request);
		return adminService.viewBidderProfile(id, request);
	}

	@GetMapping("/viewClaimDoc")
	public ClaimInsurance viewClaimDoc(@RequestParam int id, HttpServletRequest request) {
		adminService.viewClaimDoc(id, request);
		return adminService.viewClaimDoc(id, request);
	}
	
	
}
