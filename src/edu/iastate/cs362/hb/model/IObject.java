package edu.iastate.cs362.hb.model;

import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBMethodNotFoundException;
import edu.iastate.cs362.hb.model.attributes.Identifiable;
import edu.iastate.cs362.hb.model.attributes.Modifiable;
import edu.iastate.cs362.hb.model.attributes.Nameable;

/**
 * Super interface to IClass and IInterface
 * @author Brandon
 *
 */
public interface IObject extends Nameable, Identifiable, Modifiable {
	
	/**
	 * Method to add a packageName to this IObject
	 * @param packageName
	 * 				the name to apply to this IObject 
	 * @return
	 * 		true if the operation was successful, false otherwise
	 */
	public boolean addPackage(String packageName);
	
	public boolean removePackage();
	
	public String getPackage();
	
	public boolean addMethod(IMethod method) throws HBDuplicateMethodException;
	
	public boolean removeMethod(IMethod method);
	
	/**
	 * Returns the concrete method with the given name, or null 
	 * if it doesn't exist
	 * @param name
	 * @return null if not found
	 * @throws HBMethodNotFoundException 
	 */
	public IMethod getMethod(String name) throws HBMethodNotFoundException;
	
	public String list();
	
	public boolean changeName(String newName);
	
	public boolean changePackage(String packageName);
}
