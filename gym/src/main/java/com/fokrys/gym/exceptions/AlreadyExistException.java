package com.fokrys.gym.exceptions;

public class AlreadyExistException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistException(String msg) {
		super(msg);
	}
	
	public AlreadyExistException() {
		super("Exist!!");
	}
}
