package com.lti.repository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import com.lti.email.SendMail;
import com.lti.entity.Bidder;
import com.lti.entity.Bids;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.entity.User;

@Repository
public class AdminRepoImpl implements AdminRepo {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SendMail sendMail;

	@Override
	public List<User> fStatusInQueue() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("From User where status='Queued' AND role='Farmer'");
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public List<User> fList() {
		Query q = em.createQuery("From User where status='Approved' AND role='Farmer'");
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public List<User> bStatusInQueue() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("From User where status='Queued' AND role='Bidder'");
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public List<User> bList() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("From User where status='Approved' AND role='Bidder'");
		List<User> users = q.getResultList();
		return users;
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void updateCropSellRequest(int userId, int cropId, String cropSoldStatus, String adminApproval) {
		System.out.println("Admin crop sell request approval part");
		Crop crop = em.find(Crop.class, cropId);

		if (adminApproval.equalsIgnoreCase("Approved")) {
			crop.setAdminApproval(adminApproval);
			crop.setCropSoldStatus(cropSoldStatus);
			User user = crop.getFarmer();
			em.merge(crop);
			sendMail.CropAccept(user.getEmailId(), crop.getCropId(), crop.getCropName());

		} else {
			User user = crop.getFarmer();
			em.remove(crop);
			sendMail.CropReject(user.getEmailId(), crop.getCropName());

		}

	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public List<Crop> sellRequestInQueue() {
		TypedQuery<Crop> q = em.createQuery("FROM Crop c where c.adminApproval = 'Pending' ", Crop.class);
		List<Crop> lu = q.getResultList();
		return lu;

	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void finalizeAuction(int cropId) {
		// TODO Auto-generated method stub
		Crop crop = em.find(Crop.class, cropId);
		Date date = new Date();
		crop.setCropSoldStatus("Sold");
		crop.setCropSoldPrice((double) crop.getCurrentBid());
		crop.setCropSoldDate(date);
		User user = crop.getFarmer();
		em.merge(crop);

		double soldPrice = crop.getCropSoldPrice();
		sendMail.bidSuccessFarmer(user.getEmailId(), crop.getCropId(), crop.getCropName(), crop.getCropSoldPrice());
		Query q = em.createQuery("from Bids where crop_crop_id=:id");
		q.setParameter("id", cropId);
		List<Bids> bids = q.getResultList();
		for (Bids bids2 : bids) {
			if (bids2.getBidAmount() == soldPrice) {
				bids2.setStatus("Success");
				Bidder bidder = bids2.getBidder();
				sendMail.bidSuccessBidder(bidder.getEmailId(), crop.getCropId(), crop.getCropName(),
						crop.getCropSoldPrice(), bids2.getBidId());
			} else {
				bids2.setStatus("Gone");
				Bidder bidder = bids2.getBidder();
				sendMail.bidGoneBidder(bidder.getEmailId(), crop.getCropId(), crop.getCropName(), "Gone",
						bids2.getBidId());
			}
			em.merge(bids2);
		}
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void updateClaimRequest(int claimId, String claimStatus) {
		// TODO Auto-generated method stub
		ClaimInsurance claim = em.find(ClaimInsurance.class, claimId);
		Query q = em.createQuery("FROM Insurance where claim_id=:id");
		q.setParameter("id", claimId);
		List<Insurance> insurance = q.getResultList();

		if (claimStatus.equalsIgnoreCase("Approved")) {
			claim.setStatus("Approved");
			em.merge(claim);
			for (Insurance i : insurance) {
				System.out.println("Claimed");
				i.setPolicyStatus("Claimed");
				Farmer farmer = i.getFarmer();
				sendMail.claimSuccess(farmer.getEmailId(), i.getPolicyId());
				em.merge(i);
			}

		} else {
			claim.setStatus("Declined");
//			em.remove(claim);
			for (Insurance i : insurance) {
				Farmer farmer = i.getFarmer();
				i.setPolicyStatus("Active");
				sendMail.claimDeclined(farmer.getEmailId(), i.getPolicyId());

			}

		}
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public List<Insurance> viewAllPolicy() {
		TypedQuery<Insurance> q = em.createQuery("FROM Insurance ", Insurance.class);
		List<Insurance> lu = q.getResultList();
		return lu;
	}

	@Override
	public Bidder viewBidderProfile(int id, HttpServletRequest request) {
		Bidder bidder = em.find(Bidder.class, id);

		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";

		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();

		String targetFile = tempDownloadPath + bidder.getBidderAADHAR();
		String targetFile1 = tempDownloadPath + bidder.getBidderPAN();
		String targetFile2 = tempDownloadPath + bidder.getBidderLicense();

		String uploadedImagesPath = "d:/uploads/";
		String sourceFile = uploadedImagesPath + bidder.getBidderAADHAR();
		String sourceFile1 = uploadedImagesPath + bidder.getBidderPAN();
		String sourceFile2 = uploadedImagesPath + bidder.getBidderLicense();

		try {
			FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
			FileCopyUtils.copy(new File(sourceFile1), new File(targetFile1));
			FileCopyUtils.copy(new File(sourceFile2), new File(targetFile2));
		} catch (IOException e) {
			e.printStackTrace(); // hoping for no error will occur
		}

		return bidder;
	}

	@Override
	public ClaimInsurance viewClaimDoc(int id, HttpServletRequest request) {
		ClaimInsurance claimInsurance = em.find(ClaimInsurance.class, id);

		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";

		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();

		String targetFile = tempDownloadPath + claimInsurance.getDocument();

		String uploadedImagesPath = "d:/uploads/";
		String sourceFile = uploadedImagesPath + claimInsurance.getDocument();
		try {
			FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
		} catch (IOException e) {
			e.printStackTrace(); // hoping for no error will occur
		}

		return claimInsurance;

	}

	@Override
	public Farmer viewFarmerProfile(int id, HttpServletRequest request) {
		Farmer farmer = em.find(Farmer.class, id);
		String projPath = request.getServletContext().getRealPath("/");
		String tempDownloadPath = projPath + "/downloads/";

		File f = new File(tempDownloadPath);
		if (!f.exists())
			f.mkdir();

		String targetFile = tempDownloadPath + farmer.getFarmerAADHAR();
		String targetFile1 = tempDownloadPath + farmer.getFarmerPAN();
		String targetFile2 = tempDownloadPath + farmer.getFarmerCertificate();

		String uploadedImagesPath = "d:/uploads/";
		String sourceFile = uploadedImagesPath + farmer.getFarmerAADHAR();
		String sourceFile1 = uploadedImagesPath + farmer.getFarmerPAN();
		String sourceFile2 = uploadedImagesPath + farmer.getFarmerCertificate();

		try {
			FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
			FileCopyUtils.copy(new File(sourceFile1), new File(targetFile1));
			FileCopyUtils.copy(new File(sourceFile2), new File(targetFile2));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return farmer;
	}

	

}
