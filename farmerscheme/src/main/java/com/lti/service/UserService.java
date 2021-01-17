package com.lti.service;

import com.lti.dto.Login;
import com.lti.dto.Status;
import com.lti.entity.User;

public interface UserService {
	public User login(Login login);
	public void updateStatus(int userId, String status);
	public Status addOtpByUserId(String email);
	public Status verifyOtp(String email, String otp);
	public Status updateUserPasswordByEmail(String username, String password);
}
