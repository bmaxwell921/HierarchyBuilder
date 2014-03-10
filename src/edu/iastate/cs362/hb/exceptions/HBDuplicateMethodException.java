package edu.iastate.cs362.hb.exceptions;

/**
 * Exception thrown when the user tries to add a method to an Object that
 * already has that method
 * @author Brandon
 *
 */
public class HBDuplicateMethodException extends Exception {

	private static final long serialVersionUID = 1L;

	public HBDuplicateMethodException(String message, Object... args) {
		super(String.format(message, args));
	}

}
