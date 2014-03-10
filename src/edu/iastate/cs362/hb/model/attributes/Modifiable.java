package edu.iastate.cs362.hb.model.attributes;

import java.util.Set;

/**
 * Class used to define behavior for things that are modifiable
 * @author Brandon
 *
 */
public interface Modifiable {

	/**
	 * Method used to add modifiers to to this object
	 * @param modifier
	 */
	public void addModifiers(String... modifier);
	
	/**
	 * Method used to remove the given modifier from this object
	 * @param modifier
	 * @return
	 * 		true if the modifier was removed, false otherwise
	 */
	public boolean removeModifier(String modifier);
	
	/**
	 * Method used to get all the modifiers on this object
	 * @return
	 */
	public Set<String> getModifiers();
}
