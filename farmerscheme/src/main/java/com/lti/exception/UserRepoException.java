package com.lti.exception;

public class UserRepoException extends RuntimeException {
	public UserRepoException() {
		super();
	}

	public UserRepoException(String message) {
		super(message);
	}
}
