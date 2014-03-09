package edu.iastate.cs362.hb.exceptions;

/**
 * Exception thrown when multiple objects have the name of the object
 * a user looks for
 * @author Brandon
 *
 */
public class HBMultipleObjectsFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public HBMultipleObjectsFoundException(String message, Object... args) {
		super(String.format(message, args));
	}

}
