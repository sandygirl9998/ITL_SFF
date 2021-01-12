package com.lti.repository;

import java.util.List;

import com.lti.entity.User;

public interface AdminRepo {
	List<User> fStatusInQueue();
	List<User> fList();
	List<User> bStatusInQueue();
	List<User> bList();

}
