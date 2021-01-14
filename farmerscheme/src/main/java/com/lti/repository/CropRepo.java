package com.lti.repository;

import java.util.List;

import com.lti.dto.SoldHistory;
import com.lti.dto.ViewCrop;
import com.lti.entity.Bids;
import com.lti.entity.Crop;

public interface CropRepo {

	List<Crop> getActiveCrops();
	List<ViewCrop> viewCrops();
	public List<Crop> listCrop() ;
	double maxBidAmount(int cropId);
	List<Crop> marketCrops(int farmerid);
	List<Bids> previousBids(int cropid);
	List<SoldHistory> soldHistory(int farmerid);
}
