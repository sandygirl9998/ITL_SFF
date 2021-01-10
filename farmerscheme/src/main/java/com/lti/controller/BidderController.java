package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Bidder;
import com.lti.exception.UserServiceException;
import com.lti.service.BidderService;

@Controller
public class BidderController {
	
	@Autowired
	private BidderService bidderService;
	
	@PostMapping("/bregister")
	public @ResponseBody Status register(@RequestBody Bidder bidder) {
		try {
			Status s= new Status();
			bidderService.add(bidder);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Registration Successful!");
			return s;
		}
		catch(UserServiceException e) {
			Status s= new Status();
			bidderService.add(bidder);
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
	}
}
