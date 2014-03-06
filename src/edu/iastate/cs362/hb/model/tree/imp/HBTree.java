package edu.iastate.cs362.hb.model.tree.imp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTree;

/**
 * The tree structure for the Hierarchy Builder class
 * @author Brandon
 *
 */
public class HBTree implements IHBTree {

	// A single node in the tree
	private class HBNode {
		// The parent node
		public HBNode parent;
		
		// The class located at this location
		public IObject data;
		
		// All of the classes that extends the data class
		public Map<IRelationship, List<IObject>> subTypes;
		
		public HBNode(IObject data) {
			this.parent = null;
			this.data = data;
			
			subTypes = new HashMap<>();
		}
	}
	
	
	/*
	 * Since a class hierarchy can have several smaller hierarchys built into it
	 * we don't just have one root, but many
	 */
	private Map<String, HBTree> roots;
	
	/*
	 *  A storage location for Objects that haven't been placed in the hierarchy
	 */
	private List<IObject> unplacedObjects;
	
	/**
	 * Creates a new, empty class Hierarchy
	 */
	public HBTree() {
		roots = new HashMap<>();
		unplacedObjects = new LinkedList<>();
	}

	@Override
	public IObject getObject(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addObject(IObject newClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRelationship(IObject fromClass, IObject toClass,
			IRelationship relationship) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRelationship(IObject fromClass, IObject toClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeObject(IObject toRemove) {
		// TODO Auto-generated method stub
		return false;
	}


}
