package edu.iastate.cs362.hb.model.tree;

import java.util.List;

import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.IRelationship;

/**
 * Class to represent the Tree Structure build by the Hierarchy Builder project
 * 
 * @author Brandon
 * 
 */
public interface ITree {

	/**
	 * Method used to get an object from the tree
	 * 
	 * @param objName
	 * @return
	 * @throws HBObjectNotFoundException
	 *             if the object isn't found in the tree
	 * @throws HBMultipleObjectsFoundException
	 *             if multiple objects exist in the tree with this name
	 */
	public IObject getObject(String objName) throws Exception;

	/**
	 * Method used to add an object to the tree
	 * 
	 * @param newObj
	 * @return
	 * @throws HBDuplicateObjectFoundException
	 *             thrown if an object already exists in the tree with the given
	 *             name and package
	 */
	public boolean addObject(IObject newObj) throws Exception;

	/**
	 * Adds the given relationship between the two class
	 * 
	 * @param from
	 * @param to
	 * @param rel
	 * @return
	 * @throws HBObjectNotFoundException
	 *             If either fromClass or toClass don't exist in the tree
	 * @throws HBDuplicateRelationshipException
	 *             If the relationship fromClass to toClass already exists
	 */
	public boolean addRelationship(IObject from, IObject to, IRelationship rel)
			throws Exception;

	/**
	 * Removes the given relationship from the two classes
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws HBObjectNotFoundException
	 *             If either given class doesn't exist in the tree
	 * @throws HBRelationshipNotFoundException
	 *             If the relationship doesn't exist
	 */
	public boolean removeRelationship(IObject from, IObject to)
			throws Exception;

	/**
	 * Method used to remove an object from the tree.
	 * 
	 * Side effect: Any relationships to or from this object are removed
	 * 
	 * @param rem
	 * @return
	 * @throws HBObjectNotFoundException
	 *             If the given object doesn't exist in the tree
	 */
	public boolean removeObject(IObject rem) throws HBObjectNotFoundException;

	/**
	 * Method used to get a list of all the objects that match the given name.
	 * 
	 * TODO remove after we implement Id Map
	 * 
	 * @param name
	 * @return
	 */
	public List<IObjectBox> getMatchingObjects(String name);

	/**
	 * Traverses the tree hierarchy with the given visitor. Starts by going
	 * through the roots in alphabetical order and following their classes all
	 * the way down
	 * 
	 * @param visitor
	 */
	public void traverse(IHBTreeVisitor visitor);
}
