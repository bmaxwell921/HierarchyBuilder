package edu.iastate.cs362.hb.exceptions;

public class HBMethodNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a HBMethodNotFound with the given message
	 * @param message
	 */
	public HBMethodNotFoundException(String message, Object... args) {
		super(String.format(message, args));
	}
}
