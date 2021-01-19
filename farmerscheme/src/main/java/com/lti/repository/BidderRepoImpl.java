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
import com.lti.entity.Bidder;
import com.lti.entity.Bids;
import com.lti.entity.Crop;

@Repository
@Transactional
public class BidderRepoImpl implements BidderRepo {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(Bidder bidder) {
		bidder.setRole("Bidder");
		em.persist(bidder);
	}

	
	@Override
	public void deleteBidder(int bidderId) {
		em.remove(em.find(Bidder.class, bidderId));

	}

	@Override
	public void updateBidder(Bidder bidder) {
		em.merge(bidder);

	}
	
	@Override
    public void makeBid(int bidderid, int cropid, Bids bid) {
        Bidder bidder=em.find(Bidder.class, bidderid);
        bidder.getBids().add(bid);
        Crop c=em.find(Crop.class,cropid);
        c.getBids().add(bid);
        bid.setCrop(c);
        bid.setBidder(bidder);
        
        em.persist(bid);
        em.merge(bidder);
		      
       
    }


	@Override
	public List<Bids> viewOwnBids(int bidderid) {
		// TODO Auto-generated method stub
		Bidder b = em.find(Bidder.class, bidderid);
        List<Bids> bids = b.getBids();
        return bids;
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
		String uploadedCertificateFileName = document.getLicense().getOriginalFilename();
		String newFileName = id + "-" + "Aadhar" + "-" + uploadedAadharFileName;
		String newFileName1 = id + "-" + "PAN" + "-" + uploadedPANFileName;
		String newFileName2 = id + "-" + "Lic" + "-" + uploadedCertificateFileName;
		String targetFileName = imgUploadLocation + newFileName;
		String targetFileName1 = imgUploadLocation + newFileName1;
		String targetFileName2 = imgUploadLocation + newFileName2;
		try {
			FileCopyUtils.copy(document.getAadhar().getInputStream(), new FileOutputStream(targetFileName));
			FileCopyUtils.copy(document.getPAN().getInputStream(), new FileOutputStream(targetFileName1));
			FileCopyUtils.copy(document.getLicense().getInputStream(), new FileOutputStream(targetFileName2));
		} catch (IOException e) {
			e.printStackTrace(); // hoping no error would occur
			Status status = new Status();
			status.setStatus(StatusType.FAILED);
			status.setMessage("File upload failed!");
			return status;
		}
		Bidder bidder = userRepository.fetch(Bidder.class, id);
		bidder.setBidderAADHAR(newFileName);
		bidder.setBidderPAN(newFileName1);
		bidder.setBidderLicense(newFileName2);
		em.merge(bidder);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Documents uploaded!");
		return status;
	}
	
}
