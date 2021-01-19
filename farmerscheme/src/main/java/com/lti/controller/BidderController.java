package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import com.lti.entity.Bidder;
import com.lti.entity.Bids;
import com.lti.entity.Crop;
import com.lti.exception.UserServiceException;
import com.lti.repository.UserRepository;
import com.lti.service.BidderService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BidderController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BidderService bidderService;
	@PersistenceContext
	EntityManager em;

	@PostMapping("/bregister")
	public @ResponseBody Status register(@RequestBody Bidder bidder) {
		try {
			Status s = new Status();
			bidderService.add(bidder);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Registration Successful!");
			return s;
		} catch (UserServiceException e) {
			Status s = new Status();
			System.out.println(e);
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
	}

	@PostMapping("/bidder-doc")
	public @ResponseBody Status upload( Document document,HttpServletRequest request) {
		
			Status status=bidderService.uploadDocs(document,request)	;
		return status;
	}

	@PostMapping("/placebid")
	public @ResponseBody Status bidding(@RequestParam int bidderid, @RequestParam int cropid, @RequestBody Bids bid) {
		Bidder bidder=em.find(Bidder.class, bidderid);
        Crop c=em.find(Crop.class,cropid);   
        double cb=(double)c.getCurrentBid();
        double ba=bid.getBidAmount();
        if(ba>cb) {
		try {
			Status s = new Status();
			bidderService.placeBid(bidderid, cropid, bid);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Bid made successfully");
			return s;
		} catch (UserServiceException e) {
			Status s = new Status();
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
        }
        else {
        	Status s = new Status();
			s.setStatus(StatusType.FAILED);
			s.setMessage("Bid amount should be greater than current bid");
			return s;
        }
	}

	@GetMapping(value = "/bidderownbids")
	public List<Bids> viewOwnBids(@RequestParam int bidderid) {
		return bidderService.viewOwnBids(bidderid);
	}
}
