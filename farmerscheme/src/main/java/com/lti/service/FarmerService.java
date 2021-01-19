package com.lti.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lti.dto.Document;
import com.lti.dto.Status;
import com.lti.entity.Crop;

import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.repository.Policies;

public interface FarmerService {
	void add(Farmer farmer);

	void insure(int fid, Insurance insurance);

	
	void addCrop(int farmerId, Crop crop);
	List<Insurance> policies(int farmerid);

	Status uploadDocs(Document document, HttpServletRequest request);


}
