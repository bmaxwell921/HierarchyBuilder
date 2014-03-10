package edu.iastate.cs362.hb.exceptions;

/**
 * Exception thrown when the user tries to use an HBClass that 
 * doesn't exist in the design.
 * @author Alex
 */
public class HBClassNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a HBClassNotFoundException with the given message
	 * @param message
	 */
	public HBClassNotFoundException(String message, Object... args) {
		super(String.format(message, args));
	}
}
