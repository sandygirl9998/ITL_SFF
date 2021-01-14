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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
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
			Status s= new Status();
			farmerService.add(farmer);
			s.setStatus(StatusType.SUCCESS);
			s.setMessage("Registration Successful!");
			return s;
		}
		catch(UserServiceException e) {
			Status s= new Status();			
			s.setStatus(StatusType.FAILED);
			s.setMessage(e.getMessage());
			return s;
		}
	}
	@PostMapping("/apply")
	public @ResponseBody Status apply(@RequestParam("fid") int fid, @RequestBody Insurance insurance) {
		
		try {
            Status s=new Status();
            farmerService.insure(fid, insurance);
            s.setStatus(StatusType.SUCCESS);
            s.setMessage("Insurance applied successfully");
            return s;
        }
        catch(UserServiceException e) {
            Status s=new Status();
            s.setStatus(StatusType.FAILED);
            s.setMessage(e.getMessage());
            return s;
        }
		
}
	
	@PostMapping("/farmer-doc")
	public @ResponseBody Status upload(Document document,HttpServletRequest request) {
		String projPath = request.getServletContext().getRealPath("/");
		String imgUploadLocation = projPath + "/assets/";
		System.out.println(imgUploadLocation);
		//creating this downloads folder in case if it doesn't exist
		File f = new File(imgUploadLocation);
		if(!f.exists())
			f.mkdir();
		String emailId = document.getEmailId();
		int id=userRepository.fetchByEmail(document.getEmailId());
		String uploadedAadharFileName = document.getAadhar().getOriginalFilename();
		String uploadedPANFileName = document.getPAN().getOriginalFilename();
		String uploadedCertificateFileName = document.getCertificate().getOriginalFilename();
		String newFileName = id + "-" + uploadedAadharFileName;
		String newFileName1 = id + "-" + uploadedPANFileName;
		String newFileName2 = id + "-" + uploadedCertificateFileName;
		String targetFileName = imgUploadLocation + newFileName;
		String targetFileName1= imgUploadLocation + newFileName1;
		String targetFileName2 = imgUploadLocation + newFileName2;
		try {
			FileCopyUtils.copy(document.getAadhar().getInputStream(), new FileOutputStream(targetFileName));
			FileCopyUtils.copy(document.getPAN().getInputStream(), new FileOutputStream(targetFileName1));
			FileCopyUtils.copy(document.getCertificate().getInputStream(), new FileOutputStream(targetFileName2));
		} catch(IOException e) {
			e.printStackTrace(); //hoping no error would occur
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage("File upload failed!");
			return status;
		}
		
		farmerService.updateAadhar(emailId, newFileName);
		farmerService.updatePAN(emailId, newFileName1);
		farmerService.updateCertificate(emailId, newFileName2);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Documents uploaded!");
		return status;
	}
}
