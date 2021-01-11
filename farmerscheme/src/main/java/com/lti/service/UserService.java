package com.lti.service;

import com.lti.dto.Login;
import com.lti.entity.User;

public interface UserService {
	public User login(Login login);
	public void updateStatus(int userId, String status);
}
