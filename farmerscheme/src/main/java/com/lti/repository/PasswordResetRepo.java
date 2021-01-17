package com.lti.repository;

import com.lti.entity.Otp;
import com.lti.entity.User;

public interface PasswordResetRepo {

	Otp addOtpByUserId(int userId);

	User updateUserPasswordByEmailId(String emailId, String password);

}
