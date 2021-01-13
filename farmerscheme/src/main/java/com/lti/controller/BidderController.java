package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Bidder;
import com.lti.exception.UserServiceException;
import com.lti.repository.UserRepository;
import com.lti.service.BidderService;

@RestController
@CrossOrigin
public class BidderController {
	
	@Autowired
	private UserRepository userRepository;
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
	@PostMapping("/bidder-doc")
	public @ResponseBody Status upload(Document document,HttpServletRequest request) {
		String projPath = request.getServletContext().getRealPath("/");
		String imgUploadLocation = projPath + "/assets/";
		System.out.println(projPath);
		//creating this downloads folder in case if it doesn't exist
		File f = new File(imgUploadLocation);
		if(!f.exists())
			f.mkdir();
		String emailId = document.getEmailId();
		int id=userRepository.fetchByEmail(document.getEmailId());
		String uploadedAadharFileName = document.getAadhar().getOriginalFilename();
		String uploadedPANFileName = document.getPAN().getOriginalFilename();
		String uploadedLicenseFileName = document.getLicense().getOriginalFilename();
		String newFileName = id + "-" + uploadedAadharFileName;
		String newFileName1 = id + "-" + uploadedPANFileName;
		String newFileName2 = id + "-" + uploadedLicenseFileName;
		String targetFileName = imgUploadLocation + newFileName;
		String targetFileName1= imgUploadLocation + newFileName1;
		String targetFileName2 = imgUploadLocation + newFileName2;
		try {
			FileCopyUtils.copy(document.getAadhar().getInputStream(), new FileOutputStream(targetFileName));
			FileCopyUtils.copy(document.getPAN().getInputStream(), new FileOutputStream(targetFileName1));
			FileCopyUtils.copy(document.getLicense().getInputStream(), new FileOutputStream(targetFileName2));
		} catch(IOException e) {
			e.printStackTrace(); //hoping no error would occur
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage("File upload failed!");
			return status;
		}
		
		bidderService.updateAadhar(emailId, newFileName);
		bidderService.updatePAN(emailId, newFileName1);
		bidderService.updateLicense(emailId, newFileName2);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Documents uploaded!");
		return status;
	}
}
