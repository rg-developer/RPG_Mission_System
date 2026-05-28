package com.prueba1.main.rest.Exceptions;

public class DuplicatedEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public DuplicatedEmailException(String message) {
		 super(message);
	}
}
