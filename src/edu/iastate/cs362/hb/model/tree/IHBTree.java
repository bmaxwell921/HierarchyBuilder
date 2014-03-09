package edu.iastate.cs362.hb.model.tree;

import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;

/**
 * Class to represent the Tree Structure build by the Hierarchy Builder project
 * 
 * @author Brandon
 * 
 */
public interface IHBTree {

	public IObject getObject(String className) throws HBObjectNotFoundException;

	public boolean addObject(IObject newClass) throws HBDuplicateObjectFoundException;

	public boolean addRelationship(IObject fromClass, IObject toClass,
			IRelationship relationship);

	public boolean removeRelationship(IObject fromClass, IObject toClass);

	/**
	 * Method used to remove an object from the tree. 
	 * 
	 * Side effect: Any relationships to or from this object are removed
	 * @param toRemove
	 * @return
	 */
	public boolean removeObject(IObject toRemove);
}
