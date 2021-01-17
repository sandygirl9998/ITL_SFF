package com.lti.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.Login;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.email.SendMail;
import com.lti.entity.Otp;
import com.lti.entity.User;
import com.lti.exception.UserRepoException;
import com.lti.exception.UserServiceException;
import com.lti.repository.PasswordResetRepo;
import com.lti.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordResetRepo passRepo;
	@Autowired
	private SendMail sendMail;

	@Override
	public User login(Login login) {
		try {
			if (!userRepository.isUserPresent(login.getUsername()))
				throw new UserServiceException("User not registered!");
			int id = userRepository.findAccount(login);
			User user = userRepository.fetch(User.class, id);
			return user;
		}
		// catch(EmptyResultDataAccessException e) {
		catch (UserServiceException e) {
			throw new UserServiceException("User not registered!");
		} catch (UserRepoException e) {
			throw new UserRepoException("User not Approved");
		} catch (Exception e) {
			throw new UserServiceException("Incorrect email/password");
		}
	}

	@Override
	public void updateStatus(int userId, String status) {
		// TODO Auto-generated method stub
		userRepository.updateUserStatus(userId, status);
	}

	@Override
	public Status updateUserPasswordByEmail(String email, String password) {
		Status status = new Status();
		status.setStatus(StatusType.FAILED);
		status.setMessage("Could not update user password");

		int id = userRepository.updateUserPasswordByEmailId(email, password);
		User user = userRepository.fetch(User.class, id);
		if (user != null) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Updated password");
			return status;
		}
		return status;
	}

	@Override
	public Status addOtpByUserId(String emailId) {
		Status status = new Status();
		status.setStatus(StatusType.FAILED);
		status.setMessage("User not Regsistered");
		if (!userRepository.isUserPresent(emailId))
			return status;

		int userId = userRepository.fetchByEmail(emailId);
		User user = userRepository.fetch(User.class, userId);

		Otp otp = passRepo.addOtpByUserId(userId);
		try {
			String subject = "OTP for password change";
			String email = user.getEmailId();
			String text = "Hi " + user.getName() + ", use this OTP - " + otp.getOtp()
					+ ". It will expire after 5 minutes";
			sendMail.otpMail(email, text, subject);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Successfully sent");
			return status;
		} catch (UserServiceException e) {
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;

		}

	}

	@Override
	public Status verifyOtp(String emailId, String otpStr) {
		Status status = new Status();
		status.setStatus(StatusType.FAILED);
		int userId = userRepository.fetchByEmail(emailId);

		Otp otp = userRepository.getOtpByUserId(userId);
		if (otp == null) {
			status.setMessage("OTP not generated");
			return status;
		}
		int minutes = (int) ChronoUnit.MINUTES.between(otp.getTimeOfGeneration(), LocalDateTime.now());

		if (minutes > 5) {
			status.setMessage("OTP expired");
			return status;
		}
		if (otp.getOtp().equalsIgnoreCase(otpStr)) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("OTP valid");
			return status;
		}
		status.setMessage("OTP does not match");
		return status;
	}

}
