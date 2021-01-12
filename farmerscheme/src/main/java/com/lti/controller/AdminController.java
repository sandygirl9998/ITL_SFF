package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.User;
import com.lti.service.AdminService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	//fetch farmers whose status is queued
	@GetMapping(value="/farmerapproval",produces="application/json")
	public List<User> farmerInQueue(){
		return adminService.fInQueue();
	}
	
	//fetch farmers whose status is approved
	@GetMapping(value="/farmerlist",produces="application/json")
	public List<User> farmerList(){
		return adminService.fList();
	}
	
	//fetch bidders whose status is queued
		@GetMapping(value="/bidderapproval",produces="application/json")
		public List<User> bidderInQueue(){
			return adminService.bInQueue();
		}
		
		//fetch bidders whose status is approved
		@GetMapping(value="/bidderlist",produces="application/json")
		public List<User> bidderList(){
			return adminService.bList();
		}

}
