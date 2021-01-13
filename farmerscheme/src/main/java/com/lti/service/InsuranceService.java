package com.lti.service;

import com.lti.entity.Insurance;

public interface InsuranceService {

	void Apply(Insurance ins);

	Insurance search(int polid);

	void action(String status, int polid);

	
}
