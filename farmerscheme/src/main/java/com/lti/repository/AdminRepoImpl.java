package com.lti.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.entity.Bids;
import com.lti.entity.ClaimInsurance;
import com.lti.entity.Crop;
import com.lti.entity.User;

@Repository
public class AdminRepoImpl implements AdminRepo {

	@PersistenceContext
	private EntityManager em;

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
			em.merge(crop);
			// SendMail.SuccessMail(user.getEmailId(),user.getPassword(),user.getName(),
			// user.getRole());
		} else {
			em.remove(crop);
			// SendMail.DeclinedMail(user.getEmailId(),user.getPassword(),user.getName(),
			// user.getRole());
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
		em.merge(crop);
		double soldPrice = crop.getCropSoldPrice();

		Query q = em.createQuery("from Bids where crop_crop_id=:id");
		q.setParameter("id", cropId);
		List<Bids> bids = q.getResultList();
		for (Bids bids2 : bids) {
			if (bids2.getBidAmount() == soldPrice) {
				bids2.setStatus("Success");
			} else {
				bids2.setStatus("Gone");
			}
			em.merge(bids2);
		}
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void updateClaimRequest(int claimId) {
		// TODO Auto-generated method stub
		ClaimInsurance claim = em.find(ClaimInsurance.class, claimId);
		claim.setStatus("Approved");
		em.merge(claim);
		
	}

}
