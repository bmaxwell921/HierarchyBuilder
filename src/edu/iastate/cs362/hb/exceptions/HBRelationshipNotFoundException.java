package edu.iastate.cs362.hb.exceptions;

/**
 * Exception thrown when the user tries to remove a relationship that 
 * no longer exists
 * @author Brandon
 *
 */
public class HBRelationshipNotFoundException extends Exception {

	private static final long serialVersionUID = 0;
	
	public HBRelationshipNotFoundException(String message, Object... args) {
		super(String.format(message, args));
	}

}
