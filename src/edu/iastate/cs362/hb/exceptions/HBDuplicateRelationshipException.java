package edu.iastate.cs362.hb.exceptions;

/**
 * Thrown when the user attempts to add a relationship between two objects
 * that already have a relationship
 * @author Brandon
 *
 */
public class HBDuplicateRelationshipException extends Exception {

	private static final long serialVersionUID = 1L;

	public HBDuplicateRelationshipException(String message, Object... args) {
		super(String.format(message, args));
	}

}
