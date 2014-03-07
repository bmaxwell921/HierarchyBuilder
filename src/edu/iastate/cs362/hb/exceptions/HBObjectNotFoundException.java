package edu.iastate.cs362.hb.exceptions;

/**
 * Exception thrown when the user tries to use an object that 
 * doesn't exist in the design
 * @author Brandon
 *
 */
public class HBObjectNotFoundException extends Exception {
	private static final long serialVersionUID = 0;
	
	/**
	 * Creates a HBObjectNotFoundException with the given message
	 * @param message
	 */
	public HBObjectNotFoundException(String message, Object... args) {
		super(String.format(message, args));
	}

}
