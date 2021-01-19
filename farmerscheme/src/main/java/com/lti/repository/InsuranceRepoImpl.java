package com.lti.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Insurance;

@Repository
@Transactional
public class InsuranceRepoImpl implements InsuranceRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	UserRepository  userRepository;

	@Override
	public void Save(Insurance ins) {

		em.persist(ins);
	}

	@Override
	public Insurance fetch(int polid) {

		return em.find(Insurance.class, polid);
	}

	@Override
	public void update(String status, int polid) {

		Insurance i = em.find(Insurance.class, polid);
		i.setPolicyStatus(status);
		em.merge(i);
	}
	@Override
	public void claimPolicy(int policyId,ClaimInsurance claim) {
		Insurance insurance =em.find(Insurance.class,policyId);
		insurance.setClaim(claim);
		insurance.setPolicyStatus("Pending");
		em.persist(claim);		
		
		em.merge(insurance);
	}
	
	public boolean isClaimPresent(int policyId) {
		System.out.println(policyId);
		boolean b;
		Insurance insurance = userRepository.fetch(Insurance.class, policyId);
		ClaimInsurance claim = insurance.getClaim();
		if (claim == null)
			b = true;
		else
			b = false;
		return b;
	}
	
	public List<ClaimInsurance> getClaims() {
		return em.createQuery("FROM ClaimInsurance where status='Queued'").getResultList();
	}
	@Override
	public void updateClaim(ClaimInsurance claim) {
		em.merge(claim);
	}
	@Override
	public Status uploadDocs(int policyId,Document document,HttpServletRequest request) {
//		String projPath = request.getServletContext().getRealPath("/");
//		String imgUploadLocation = projPath + "/assets/";
//		System.out.println(imgUploadLocation);
		String imgUploadLocation = "d:/uploads/";
		System.out.println(imgUploadLocation);
		//creating this downloads folder in case if it doesn't exist
		File f = new File(imgUploadLocation);
		if(!f.exists())
			f.mkdir();
		String uploadedFileName = document.getClaimDocument().getOriginalFilename();
		String newFileName = policyId + "-" + uploadedFileName;
		String targetFileName = imgUploadLocation + newFileName;
		try {
			FileCopyUtils.copy(document.getClaimDocument().getInputStream(), new FileOutputStream(targetFileName));
		} catch(IOException e) {
			e.printStackTrace(); //hoping no error would occur
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage("File upload failed!");
			return status;
		}
		System.out.println(policyId);
		Insurance insurance = userRepository.fetch(Insurance.class, policyId);
		int id =insurance.getClaim().getClaimId();
		ClaimInsurance claim = userRepository.fetch(ClaimInsurance.class, id);
		claim.setDocument(newFileName);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Documents uploaded!");
		return status;
	
	}
}
