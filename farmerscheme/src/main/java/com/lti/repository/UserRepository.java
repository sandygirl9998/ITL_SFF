package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.dto.Login;

@Repository
public class UserRepository extends GenericRepository {
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

}
