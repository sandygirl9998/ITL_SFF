package com.lti.repository;

import java.util.List;

import com.lti.dto.ViewCrop;
import com.lti.entity.Crop;

public interface CropRepo {

	List<Crop> getActiveCrops();
	List<ViewCrop> viewCrops();
	public List<Crop> listCrop() ;
	double maxBidAmount(int cropId);
}
