package edu.iastate.cs362.hb.model.attributes;

/**
 * Behavior for classes that can have names
 * @author Brandon
 *
 */
public interface Nameable {

	/**
	 * Returns the name of this Nameable
	 * @return
	 */
	public String getName();
	
	/**
	 * Returns whether this Nameable has the same name as the given string
	 * @param name
	 * @return
	 */
	public boolean hasName(String name);
}
