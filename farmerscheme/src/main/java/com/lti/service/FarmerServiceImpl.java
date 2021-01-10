package com.lti.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Farmer;
import com.lti.exception.UserServiceException;
import com.lti.repository.FarmerRepo;
import com.lti.repository.UserRepository;
@Service
@Transactional
public class FarmerServiceImpl implements FarmerService{

	

		@Autowired
		private FarmerRepo farmerRepository;
		@Autowired
		private UserRepository userRepository;
		
		@Override
		public void add(Farmer farmer) {
			if(userRepository.isUserPresent(farmer.getEmailId()))
				throw new UserServiceException("Farmer already registered!");
			else {
				//customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
				farmerRepository.save(farmer);
				//code to send email to the customer on successful registration will be here
				}
		}

			
		}


