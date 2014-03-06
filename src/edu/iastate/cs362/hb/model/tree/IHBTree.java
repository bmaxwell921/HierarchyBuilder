package edu.iastate.cs362.hb.model.tree;

import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IRelationship;

/**
 * Class to represent the Tree Structure build by the Hierarchy Builder project
 * 
 * @author Brandon
 * 
 */
public interface IHBTree {

	public IClass getClass(String className);

	public boolean addClass(IClass newClass);

	public boolean addRelationship(IClass fromClass, IClass toClass,
			IRelationship relationship);

	public boolean removeRelationship(IClass fromClass, IClass toClass);

	public boolean removeClass(IClass toRemove);
}
