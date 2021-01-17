package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.dto.Login;
import com.lti.email.SendMail;
import com.lti.entity.Otp;
import com.lti.entity.User;
import com.lti.exception.UserRepoException;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private SendMail sendMail;
	
	public boolean isUserPresent(String email) {
		boolean b=(Long)
				entityManager
				.createQuery("select count(u.userId) from User u where u.emailId = :email")
				.setParameter("email", email)
				.getSingleResult() == 1 ? true : false;
		
		return b;
	}
	
	public int findAccount(Login login) {
		Boolean i=(Long)entityManager
				.createQuery("select count(u.userId) from User u WHERE u.emailId=:uname AND u.password=:pwd AND u.role=:rol AND u.status='Queued'")
				.setParameter("uname", login.getUsername())
				.setParameter("pwd",login.getPassword())
				.setParameter("rol", login.getRole()).getSingleResult()== 1 ? true : false;
				if(i)
					throw new UserRepoException("User not Approved");
			
				
				return  (Integer)entityManager
						.createQuery("select u.userId from User u WHERE u.emailId=:uname AND u.password=:pwd AND u.role=:rol AND u.status='Approved'")
						.setParameter("uname", login.getUsername())
						.setParameter("pwd",login.getPassword())
						.setParameter("rol", login.getRole()).getSingleResult();					
		
	}
	public <E> E fetch(Class<E> clazz, Object pk) {
		E e = entityManager.find(clazz, pk); 
		return e;
	}
	
	@Transactional(value = TxType.REQUIRED)
	@Override
	public void updateUserStatus(int userId, String status) {
		// TODO Auto-generated method stub
		User user = entityManager.find(User.class, userId);
		if (status.equalsIgnoreCase("Approved")) {
			user.setStatus(status);

			entityManager.merge(user);
//			Commenting this as of now
			sendMail.SuccessMail(user.getEmailId(),user.getName(), user.getRole());
		} else {
			entityManager.remove(user);
			sendMail.DeclinedMail(user.getEmailId(),user.getName(), user.getRole());
		}
		
	}
	
	public int fetchByEmail(String email) {
		return  (Integer)entityManager
				.createQuery("select u.userId from User u WHERE u.emailId=:uname ")
				.setParameter("uname", email).getSingleResult();
	}
	
	@Override
	 public  Otp getOtpByUserId(int userId) {
		Otp otp = null;
		try {
		otp=(Otp)entityManager
			.createQuery("From Otp o WHERE o.user_id=:uname ")
			.setParameter("uname", userId).getSingleResult();
		}
		 catch(NoResultException e) {
			 
		 }
		return otp;
	 }
	@Transactional
	@Override
	public int updateUserPasswordByEmailId(String email, String password) {
		System.out.println(password);
		Query q =entityManager.createQuery("update User u set u.password=:pass where u.emailId=:uname")
		.setParameter("uname", email)
		.setParameter("pass",password);
		q.executeUpdate();
		System.out.println(email);
		return  (Integer)entityManager
				.createQuery("select u.userId from User u WHERE u.emailId=:uname ")
				.setParameter("uname", email).getSingleResult();
	}
}
