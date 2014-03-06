package edu.iastate.cs362.hb.model.attributes;

/**
 * Interface used to define behavior for identifiable objects
 * @author Brandon
 *
 */
public interface Identifiable {

	/**
	 * Returns this Identifiable's id
	 * @return
	 */
	public long getId();
	
	/**
	 * Returns whether this Identifiable's id matches
	 * the given id
	 * @param id
	 * @return
	 */
	public boolean hasId(long id);
}
