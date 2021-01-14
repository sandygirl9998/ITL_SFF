package com.lti.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.SoldHistory;
import com.lti.dto.ViewCrop;
import com.lti.entity.Bids;
import com.lti.entity.Crop;
import com.lti.repository.CropRepo;
import com.lti.service.CropService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CropController {

	@Autowired
	private CropService cropService;
	@Autowired
	private CropRepo cropRepo;
	@PersistenceContext
	protected EntityManager entityManager;

	@GetMapping(value = "/getCrop")
	public List<Crop> getCrop() {
		return cropService.getCrop();

	}

	@GetMapping(value = "/viewCrops")
	public List<ViewCrop> getCrops() {
		return cropService.getCrops();

	}
	@Transactional(value = TxType.REQUIRED)
	@GetMapping(value="/cropsforbidder")
    public List<Crop> cropsForBidding(){
        List<Crop> crops=cropService.activeCrops();
        for (Crop crop : crops) {
            crop.setCurrentBid(cropService.currentBidAmount(crop.getCropId()));
            System.out.println(crop.getCurrentBid());
            entityManager.merge(crop);
        }
        return crops;
    }
	
	@GetMapping(value = "/viewmarketplace")
    public List<Crop> viewMarketPlace(@RequestParam int farmerid) {
        return cropService.cropsInMarket(farmerid);
    }
    @GetMapping(value = "/viewmarketplace1")
    public List<Bids> viewPreviousBids(@RequestParam int cropid) {
        return cropService.viewPreviousBids(cropid);
    }

 

    @GetMapping(value = "/soldhistory")
    public List<SoldHistory> cropSoldHistory(@RequestParam int farmerid) {
        return cropService.cropSoldHistory(farmerid);
    }
	


}
