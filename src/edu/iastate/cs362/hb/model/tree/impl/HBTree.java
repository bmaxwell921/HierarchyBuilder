package edu.iastate.cs362.hb.model.tree.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iastate.cs362.hb.constants.ErrorMessages;
import edu.iastate.cs362.hb.exceptions.HBDuplicateClassFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTree;

/**
 * The tree structure for the Hierarchy Builder class
 * @author Brandon
 *
 */
public class HBTree implements IHBTree {

	// A single node in the tree. Protected 
	private static class HBNode {
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HBNode other = (HBNode) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}
	}
	
	
	/*
	 * Since a class hierarchy can have several smaller hierarchys built into it
	 * we don't just have one root, but many
	 */
	private Map<String, HBNode> roots;
	
	/*
	 *  Rather than actually work the nodes as a tree, we'll have a map from
	 *  names to HBNode. This way we can have a fast lookups for the nodes themselves.
	 *  Nodes will be a super-set of roots
	 */
	private Map<String, HBNode> nodes;
	
	/**
	 * Creates a new, empty class Hierarchy
	 */
	public HBTree() {
		roots = new HashMap<>();
		nodes = new HashMap<>();
	}

	@Override
	public IObject getObject(String className) throws HBObjectNotFoundException {
		IObject ret  = findNode(className).data;
		if (ret == null) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND);
		}
		return ret;
	}

	@Override
	public boolean addObject(IObject newClass) throws HBDuplicateClassFoundException {
		HBNode add = new HBNode(newClass);
		if (nodes.containsValue(add) || roots.containsValue(add)) {
			throw new HBDuplicateClassFoundException(ErrorMessages.DUPLICATE_OBJECT_FOUND, newClass.getName(), newClass.getPackage());
		}
		nodes.put(newClass.getName(), add);
		return true;
	}

	@Override
	public boolean addRelationship(IObject fromClass, IObject toClass,
			IRelationship relationship) {
		// TODO Auto-generated method stub
		// Make sure to remove toClass from roots
		return false;
	}

	@Override
	public boolean removeRelationship(IObject fromClass, IObject toClass) {
		// TODO Auto-generated method stub
		// Re-add toClass to roots
		return false;
	}

	@Override
	public boolean removeObject(IObject toRemove) {
		// TODO Auto-generated method stub
		// Can be in either roots or nodes
		return false;
	}

	/*
	 *  Searches the unplaced Objects for an object with the requested name.
	 *  Returns null if no object is found with the given name
	 */
	private HBNode findNode(String name) {
		if (!roots.containsKey(name)) {
			return nodes.get(name);
		}
		return roots.get(name);
	}
}
