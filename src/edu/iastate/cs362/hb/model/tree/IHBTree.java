package edu.iastate.cs362.hb.model.tree;

import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;

/**
 * Class to represent the Tree Structure build by the Hierarchy Builder project
 * 
 * @author Brandon
 * 
 */
public interface IHBTree {

	/**
	 * Method used to get an object from the tree
	 * 
	 * @param className
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException 
	 */
	public IObject getObject(String className) throws HBObjectNotFoundException, HBMultipleObjectsFoundException;

	/**
	 * Method used to add an object to the tree
	 * 
	 * @param newClass
	 * @return
	 * @throws HBDuplicateObjectFoundException
	 *             thrown if an object already exists in the tree with the given
	 *             name and package
	 */
	public boolean addObject(IObject newClass)
			throws HBDuplicateObjectFoundException;

	/**
	 * Adds the given relationship between the two class
	 * 
	 * @param fromClass
	 * @param toClass
	 * @param relationship
	 * @return
	 */
	public boolean addRelationship(IObject fromClass, IObject toClass,
			IRelationship relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException;

	/**
	 * Removes the given relationship from the two classes
	 * 
	 * @param fromClass
	 * @param toClass
	 * @return
	 */
	public boolean removeRelationship(IObject fromClass, IObject toClass)
			throws HBObjectNotFoundException, HBRelationshipNotFoundException;

	/**
	 * Method used to remove an object from the tree.
	 * 
	 * Side effect: Any relationships to or from this object are removed
	 * 
	 * @param toRemove
	 * @return
	 */
	public boolean removeObject(IObject toRemove)
			throws HBObjectNotFoundException;
}
