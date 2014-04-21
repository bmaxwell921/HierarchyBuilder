package edu.iastate.cs362.hb.model;

import java.util.Set;

import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBMethodNotFoundException;
import edu.iastate.cs362.hb.model.attributes.Identifiable;
import edu.iastate.cs362.hb.model.attributes.Modifiable;
import edu.iastate.cs362.hb.model.attributes.Nameable;
import edu.iastate.cs362.hb.model.tree.Pair;

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
	
	/**
	 * Adds the given relationship to this object. 
	 * 	Example:
	 * 		If we have the relationship: "this extends fromObject"
	 * @param rel
	 * @param toObj
	 * @return
	 * @throws Exception 
	 */
	public boolean addRelationship(IRelationship rel, IObject fromObj) throws Exception;
	
	/**
	 * Removes the relationship from fromObj to this object.
	 * @param fromObj
	 * @return
	 * @throws RelationshipNotFoundException
	 * 		If there is not relationship from fromObj to this
	 */
	public boolean removeRelationship(IObject fromObj) throws Exception;
	
	/**
	 * Returns whether this object has from as a superType
	 * @param from
	 * @return
	 */
	public boolean hasRelationship(IObject from);
	
	/**
	 * Gets all of the pairs in this object.
	 * 	The returned instance should not be modified
	 * @return
	 */
	public Set<Pair<IRelationship, IObject>> getRelationships();
	
	public String list();
	
	public boolean changeName(String newName);
	
	public boolean changePackage(String packageName);
}
