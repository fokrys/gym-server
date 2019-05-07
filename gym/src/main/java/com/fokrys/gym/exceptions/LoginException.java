package com.fokrys.gym.exceptions;

public class LoginException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	public LoginException() {
		super("Login exception!!");
	}

	public LoginException(String msg) {
		super(msg);
	}
	
	
	
}
