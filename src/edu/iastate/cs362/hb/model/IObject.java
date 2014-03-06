package edu.iastate.cs362.hb.model;

import edu.iastate.cs362.hb.model.attributes.Identifiable;
import edu.iastate.cs362.hb.model.attributes.Nameable;

/**
 * Super interface to IClass and IInterface
 * @author Brandon
 *
 */
public interface IObject extends Nameable, Identifiable {
	
	/**
	 * Method to add a packageName to this IObject
	 * @param packageName
	 * 				the name to apply to this IObject 
	 * @return
	 * 		true if the operation was successful, false otherwise
	 */
	public boolean addPackage(String packageName);
	
	public boolean removePackage();

}
