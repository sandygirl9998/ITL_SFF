package com.lti.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;

@Repository
@Transactional
public class FarmerRepoImpl implements FarmerRepo {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(Farmer farmer) {
		farmer.setRole("Farmer");
		em.persist(farmer);
	}

	@Override
	public void deleteFarmer(int farmerId) {
		em.remove(em.find(Farmer.class, farmerId));
	}

	@Override
	public void updateFarmer(Farmer farmer) {
		em.merge(farmer);

	}

	@Override
	public void addInsurance(int farmerId, Insurance insurance) {
		Farmer farmer = em.find(Farmer.class, farmerId);
		System.out.println("hello");
		farmer.getInsurance().add(insurance);
		System.out.println("hello");
		insurance.setFarmer(farmer);
		em.persist(insurance);
		System.out.println("hello");
		em.merge(farmer);

	}

	@Override

	public void sellRequest(int farmerId, Crop crop) {

		Farmer farmer = em.find(Farmer.class, farmerId);
		farmer.getCrops().add(crop);
		double d = crop.getCropBasePrice();
		crop.setCurrentBid(d);
		crop.setFarmer(farmer);
		em.persist(crop);
		em.merge(farmer);
		
	}
	
	@Override
	public List<Insurance> getinsurance(int farmerid) {

		Query q = em.createQuery("from Insurance where farmerid=:id");
		q.setParameter("id", farmerid);
		List<Insurance> lu = q.getResultList();
		return lu;
	}
	@Override
	public Status uploadDocs(Document document, HttpServletRequest request) {
//		String projPath = request.getServletContext().getRealPath("/");
//		String imgUploadLocation = projPath + "/assets/";
		String imgUploadLocation = "d:/uploads/";
		System.out.println(imgUploadLocation);
		// creating this downloads folder in case if it doesn't exist
		File f = new File(imgUploadLocation);
		if (!f.exists())
			f.mkdir();
		String emailId = document.getEmailId();
		int id = userRepository.fetchByEmail(document.getEmailId());
		String uploadedAadharFileName = document.getAadhar().getOriginalFilename();
		String uploadedPANFileName = document.getPAN().getOriginalFilename();
		String uploadedCertificateFileName = document.getCertificate().getOriginalFilename();
		String newFileName = id + "-" + "Aadhar" + "-" + uploadedAadharFileName;
		String newFileName1 = id + "-" + "PAN" + "-" + uploadedPANFileName;
		String newFileName2 = id + "-" + "Cert" + "-" + uploadedCertificateFileName;
		String targetFileName = imgUploadLocation + newFileName;
		String targetFileName1 = imgUploadLocation + newFileName1;
		String targetFileName2 = imgUploadLocation + newFileName2;
		try {
			FileCopyUtils.copy(document.getAadhar().getInputStream(), new FileOutputStream(targetFileName));
			FileCopyUtils.copy(document.getPAN().getInputStream(), new FileOutputStream(targetFileName1));
			FileCopyUtils.copy(document.getCertificate().getInputStream(), new FileOutputStream(targetFileName2));
		} catch (IOException e) {
			e.printStackTrace(); // hoping no error would occur
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage("File upload failed!");
			return status;
		}
		Farmer farmer = userRepository.fetch(Farmer.class, id);
		farmer.setFarmerAADHAR(newFileName);
		farmer.setFarmerPAN(newFileName1);
		farmer.setFarmerCertificate(newFileName2);
		em.merge(farmer);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Documents uploaded!");
		return status;
	}

	
}
