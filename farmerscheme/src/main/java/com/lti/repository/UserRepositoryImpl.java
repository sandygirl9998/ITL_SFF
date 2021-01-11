package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.lti.dto.Login;
import com.lti.email.SendMail;
import com.lti.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	protected EntityManager entityManager;
	
	public boolean isUserPresent(String email) {
		boolean b=(Long)
				entityManager
				.createQuery("select count(u.userId) from User u where u.emailId = :email")
				.setParameter("email", email)
				.getSingleResult() == 1 ? true : false;
		
		return b;
	}
	
	public int findAccount(Login login) {
		
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
			SendMail.SuccessMail(user.getEmailId(),user.getPassword(),user.getName(), user.getRole());
		} else {
			entityManager.remove(user);
			SendMail.DeclinedMail(user.getEmailId(),user.getPassword(),user.getName(), user.getRole());
		}
		
	}
	

}
