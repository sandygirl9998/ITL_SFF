package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.Login;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User login(Login login) {
		try {
			if(!userRepository.isUserPresent(login.getUsername()))
				throw new UserServiceException("User not registered!");
			int id = userRepository.findAccount(login);
			User user = userRepository.fetch(User.class, id);
			return user;
		}
		//catch(EmptyResultDataAccessException e) {
		catch(UserServiceException e) {
			throw new UserServiceException("User not registered!");
		}
		catch(Exception e) {
			throw new UserServiceException("Incorrect email/password");
		}
	}
	@Override
	public void updateStatus(int userId, String status) {
		// TODO Auto-generated method stub
		userRepository.updateUserStatus(userId, status);
	}
	
	
}
