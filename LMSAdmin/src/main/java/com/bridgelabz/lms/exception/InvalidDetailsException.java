package com.bridgelabz.lms.exception;
public class InvalidDetailsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidDetailsException(int statusCode, String statusmessage)
	{
		super(statusmessage);
	}

}
