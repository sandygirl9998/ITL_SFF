package com.lti.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.dto.ViewCrop;
import com.lti.entity.Crop;

@Repository
@Transactional(value = TxType.REQUIRED)
public class CropRepoImpl implements CropRepo {

	@PersistenceContext
	private EntityManager em;

	public List<Crop> listCrop() {
		return em.createQuery("from Crop").getResultList();
	}

	@Override

	public List<ViewCrop> viewCrops() {
		System.out.println("hello");
		Query query = em.createQuery(
				"select cropId,cropName,cropType,fertilizer,cropBasePrice,soilPH from Crop where cropSoldStatus='Queued'");
		
		List<Object[]> crops = query.getResultList();
		System.out.println(crops);
		List<ViewCrop> viewCrop = new ArrayList<>();
		for (Object[] obj : crops) {
			ViewCrop c = new ViewCrop();
			c.setCropId((int) obj[0]);
			System.out.println(c.getCropId());
			c.setCropName((String) obj[1]);
			c.setCropType((String) obj[2]);
			c.setFertilizer((String) obj[3]);
			c.setCropBasePrice((double) obj[4]);
			c.setSoilPH((String) obj[5]);
			viewCrop.add(c);
		}
		return viewCrop;
	}

	@Override
	public List<Crop> getActiveCrops() {
		Query query = em.createQuery("from Crop where cropSoldStatus='In Market'and adminApproval='Approved'");
		List<Crop> crops = query.getResultList();
		return crops;
	}
//
//	@Override
//	public double maxBidAmount(int cropid) {
//		Crop crop = em.find(Crop.class, cropid);
//		Query q = em.createNativeQuery("select count(*) from crop_bids where crop_crop_id=:id");
//		q.setParameter("id", cropid);
//		BigDecimal bd = (BigDecimal) q.getSingleResult();
//		int res = bd.intValue();
//		
//		if (res == 0) {
//
//			double cb = (double) crop.getCurrentBid();
//
//			return cb;
//		} else {
//			System.out.println(crop.getCropId());
//			Query query = em.createNativeQuery(
//					"select max(bid_amount) from bids where bid_id in (select bids_bid_id from crop_bids where crop_crop_id=:id)");
//		
//			query.setParameter("id", cropid);
//			BigDecimal bd1 = (BigDecimal) query.getSingleResult();
//			Double d1 = bd1.doubleValue();
//			
//			return d1;
//		}
//	}
	
	public double maxBidAmount(int cropid) {
        Crop crop = em.find(Crop.class, cropid);
        Query q = em.createNativeQuery("select count(*) from crop_bids where crop_crop_id=:id");
        q.setParameter("id", cropid);
        BigDecimal bd = (BigDecimal) q.getSingleResult();
        int res = bd.intValue();
        System.out.println(res);
        if (res == 0) {

 

            double cb = (double) crop.getCurrentBid();

 

            return cb;
        } else {
            System.out.println(crop.getCropId());
            Query query = em.createNativeQuery(
                    "select max(bid_amount) from Bids where bid_id in (select bids_bid_id from crop_bids where crop_crop_id=:id)");
            System.out.println(query);
            query.setParameter("id", cropid);
            BigDecimal bd1 = (BigDecimal) query.getSingleResult();
            Double d1 = bd1.doubleValue();
            System.out.println(d1);
            return d1;
        }
    }

}
