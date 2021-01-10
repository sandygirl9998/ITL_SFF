package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Bidder;
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

		
	}


