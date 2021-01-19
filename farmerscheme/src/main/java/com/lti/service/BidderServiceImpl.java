package com.lti.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.entity.Bidder;
import com.lti.entity.Bids;
import com.lti.exception.UserServiceException;
import com.lti.repository.BidderRepo;
import com.lti.repository.UserRepository;

@Service //recommended when writing service classes
@Transactional
public class BidderServiceImpl implements BidderService {

	@Autowired
	private BidderRepo bidderRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void add(Bidder bidder) {
		if(userRepository.isUserPresent(bidder.getEmailId()))
			throw new UserServiceException("Bidder already registered!");
		else {
			//customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
			bidderRepository.save(bidder);
			//code to send email to the customer on successful registration will be here
			}
	}
	public void updateAadhar(String emailId, String newFileName) {
		int id = userRepository.fetchByEmail(emailId);
		Bidder bidder=userRepository.fetch(Bidder.class, id);
		bidder.setBidderAADHAR(newFileName);
		bidderRepository.updateBidder(bidder);
		}
	public void updatePAN(String emailId, String newFileName) {
		int id = userRepository.fetchByEmail(emailId);
		Bidder bidder=userRepository.fetch(Bidder.class, id);
		bidder.setBidderPAN(newFileName);
		bidderRepository.updateBidder(bidder);
		}
	public void updateLicense(String emailId, String newFileName) {
		int id = userRepository.fetchByEmail(emailId);
		Bidder bidder=userRepository.fetch(Bidder.class, id);
		bidder.setBidderLicense(newFileName);
		bidderRepository.updateBidder(bidder);
		}
	@Override
	public void placeBid(int bidderid, int cropid, Bids bid) {
		bidderRepository.makeBid(bidderid, cropid, bid);
		
	}
	@Override
	public List<Bids> viewOwnBids(int bidderid) {
		// TODO Auto-generated method stub
		return bidderRepository.viewOwnBids(bidderid);
	}
	
	@Override
	public Status uploadDocs(Document document,HttpServletRequest request) {
		Status status=bidderRepository.uploadDocs(document,request);
		return status;
	}
		
	}


