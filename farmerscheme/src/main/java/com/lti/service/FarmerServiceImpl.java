package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.exception.UserServiceException;
import com.lti.repository.FarmerRepo;
import com.lti.repository.Policies;
import com.lti.repository.UserRepository;

@Service
@Transactional
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepo farmerRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void add(Farmer farmer) {
		if (userRepository.isUserPresent(farmer.getEmailId()))
			throw new UserServiceException("Farmer already registered!");
		else {
			// customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
			farmerRepository.save(farmer);
			// code to send email to the customer on successful registration will be here
		}
	}

		@Override
		public void insure(int fid, Insurance insurance) {
			farmerRepository.addInsurance(fid, insurance);
			
		}

			
		

	public void updateAadhar(String emailId, String newFileName) {
		int id = userRepository.fetchByEmail(emailId);
		Farmer farmer = userRepository.fetch(Farmer.class, id);
		farmer.setFarmerAADHAR(newFileName);
		farmerRepository.updateFarmer(farmer);
	}


	public void updatePAN(String emailId, String newFileName) {
		int id = userRepository.fetchByEmail(emailId);
		Farmer farmer = userRepository.fetch(Farmer.class, id);
		farmer.setFarmerPAN(newFileName);
		farmerRepository.updateFarmer(farmer);
	}

	public void updateCertificate(String emailId, String newFileName) {
		int id = userRepository.fetchByEmail(emailId);
		Farmer farmer = userRepository.fetch(Farmer.class, id);
		farmer.setFarmerCertificate(newFileName);
		farmerRepository.updateFarmer(farmer);
	}
	
	@Override
	
	public void addCrop(int farmerId, Crop crop) {
		
		farmerRepository.sellRequest(farmerId,crop);
		
	}
	

	@Override
	public List<Policies> policies(int farmerid) {
		System.out.println("hello");
		return farmerRepository.getinsurance(farmerid);
	}
}
