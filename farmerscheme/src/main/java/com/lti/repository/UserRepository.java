package com.lti.repository;

import com.lti.dto.Login;

public interface UserRepository  {

	
	
	public boolean isUserPresent(String email) ;	
	public int findAccount(Login login) ;		
	void updateUserStatus(int userId, String status);
	public <E> E fetch(Class<E> clazz, Object pk) ;
	
	

}
