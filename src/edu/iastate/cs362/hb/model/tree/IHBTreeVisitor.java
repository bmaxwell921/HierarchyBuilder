package edu.iastate.cs362.hb.model.tree;

import java.util.Set;

import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;

/**
 * Visitor interface for tree traversals
 * @author Brandon
 *
 */
public interface IHBTreeVisitor {
	
	/**
	 * Method called when visiting an object in the HBTree
	 * @param o
	 * @param superTypes
	 */
	public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes);
}
