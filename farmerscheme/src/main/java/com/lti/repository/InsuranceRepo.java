package com.lti.repository;
import com.lti.entity.Insurance;


public interface InsuranceRepo {
	
	void Save(Insurance ins);
	Insurance fetch(int polid);
   void update(String status, int polid);
	



}
