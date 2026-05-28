package com.prueba1.main.rest.Exceptions;

public class InvalidRewardException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	 public InvalidRewardException(String message) {
		 super(message);
	}
}
