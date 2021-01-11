package com.lti.repository;

import com.lti.dto.Login;

public interface UserRepository  {

	
	
	public boolean isUserPresent(String email) ;	
	public int findAccount(Login login) ;			
	public <E> E fetch(Class<E> clazz, Object pk) ;
	
	

}
