package edu.iastate.cs362.hb.model;

/**
 * Interface for arguments coming into a method
 * @author Brandon
 *
 */
public interface IArgument {

	/**
	 * Returns the argument's type
	 * @return
	 */
	public String getType();
	
	/**
	 * Gets the argument's name
	 * @return
	 */
	public String getName();
}
