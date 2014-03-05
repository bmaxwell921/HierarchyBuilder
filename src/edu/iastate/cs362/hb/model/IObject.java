package edu.iastate.cs362.hb.model;

/**
 * Super interface to IClass and IInterface
 * @author Brandon
 *
 */
public interface IObject {
	
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
