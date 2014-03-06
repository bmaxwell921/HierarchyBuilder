package edu.iastate.cs362.hb.model.tree;

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

	public boolean addObject(IObject newClass);

	public boolean addRelationship(IObject fromClass, IObject toClass,
			IRelationship relationship);

	public boolean removeRelationship(IObject fromClass, IObject toClass);

	public boolean removeObject(IObject toRemove);
}
