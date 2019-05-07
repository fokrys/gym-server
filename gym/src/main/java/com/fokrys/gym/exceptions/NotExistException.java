package com.fokrys.gym.exceptions;

public class NotExistException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public NotExistException(String msg) {
		super(msg);
	}
	
	public NotExistException() {
		super("Doesn't exist!");
	}
}
