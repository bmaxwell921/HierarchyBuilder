package edu.iastate.cs362.hb.exceptions;

public class HBClassNotFoundException extends Exception {

	/**
	 * Exception thrown when the user tries to use an HBClass that 
	 * doesn't exist in the design.
	 * @author Alex
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a HBClassNotFoundException with the given message
	 * @param message
	 */
	public HBClassNotFoundException(String message, Object... args) {
		super(String.format(message, args));
	}
}
