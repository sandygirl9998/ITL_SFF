package com.lti.service;

import java.util.List;

import com.lti.dto.SoldHistory;
import com.lti.dto.ViewCrop;
import com.lti.entity.Bids;
import com.lti.entity.Crop;

public interface CropService {

	public List<ViewCrop> getCrops();
	public List<Crop> getCrop() ;
	public List<Crop> activeCrops();
	public double currentBidAmount(int cropId);
	public List<Crop> cropsInMarket(int farmerid);
	public List<Bids> viewPreviousBids(int cropid);
	public List<SoldHistory> cropSoldHistory(int farmerid);
	
}
