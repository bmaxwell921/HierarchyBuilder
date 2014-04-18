package edu.iastate.cs362.hb.exceptions;

/**
 * Exception used when the user inputs a malformatted command
 * @author Brandon
 *
 */
public class MalformattedInputException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a new MalformattedCommandException with the given message
	 * @param message
	 */
	public MalformattedInputException(String message, Object... args) {
		super(String.format(message, args));
	}

}
