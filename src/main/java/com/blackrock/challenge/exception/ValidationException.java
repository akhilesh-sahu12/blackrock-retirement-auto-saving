package com.blackrock.challenge.exception;

public class ValidationException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }
}