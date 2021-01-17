package com.lti.repository;


	import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.entity.Otp;
import com.lti.entity.User;

	@Repository

public class PasswordResetRepoImpl implements PasswordResetRepo {
		
		@Autowired
		private UserRepository userRepository;
		
		@PersistenceContext
		private EntityManager em;
		
		@Transactional
		@Override
		public Otp addOtpByUserId(int userId) {
			Random r = new Random();
		    String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
			
			Otp otp= userRepository.getOtpByUserId(userId);
			if (otp==null) {
				otp = new Otp();
				otp.setUser_id(userId);
			}
			otp.setTimeOfGeneration(LocalDateTime.now());
			otp.setOtp(randomNumber);
			return em.merge(otp);
		}
		
	 @Transactional
	 @Override
		public User updateUserPasswordByEmailId(String emailId, String password) {
			int id = userRepository.fetchByEmail(emailId);
			User user=userRepository.fetch(User.class, id);
			if (user== null ) return null;
			user.setPassword(password);
			return em.merge(user);
		}

	}

