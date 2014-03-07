package edu.iastate.cs362.hb.exceptions;

/**
 * Exception thrown when the user tries to add duplicate classes to the Hierarchy
 * @author Brandon
 *
 */
public class HBDuplicateObjectFoundException extends Exception {
	private static final long serialVersionUID = 0;

	public HBDuplicateObjectFoundException(String message, Object... args) {
		super(String.format(message, args));
	}

}
