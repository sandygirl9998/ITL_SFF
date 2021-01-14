package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.ViewCrop;
import com.lti.entity.Crop;
import com.lti.repository.CropRepo;

@Service
@Transactional
public class CropServiceImpl  implements CropService{

	@Autowired
	private CropRepo cropRepository;
	@Override
	public List<ViewCrop> getCrops() {
		return cropRepository.viewCrops();
		
	}
	@Override
	public List<Crop> getCrop() {
		return cropRepository.listCrop();
	}
	@Override
	public List<Crop> activeCrops() {
		// TODO Auto-generated method stub
		return cropRepository.getActiveCrops();
	}
	@Override
	public double currentBidAmount(int cropId) {
		// TODO Auto-generated method stub
		return cropRepository.maxBidAmount(cropId);
	}

}
