package Exceptions;

public class InvalidMissionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public InvalidMissionException(String message) {
		 super(message);
	}
}
