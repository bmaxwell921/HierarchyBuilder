package edu.iastate.cs362.hb.model.tree.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.constants.ErrorMessages;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.ITree;

/**
 * The tree structure for the Hierarchy Builder class
 * 
 * @author Brandon
 * 
 */
public class HBTree implements ITree {

	/*
	 * Map of all the objects in the tree. Value is the set of all relationships
	 * where the key is the 'to' object.
	 * 
	 * I decided to sacrifice speed for clarity because it was getting out of
	 * hand trying to balance everything
	 */
	private Set<IObject> objs;

	/**
	 * Constructs a new, empty HBTree
	 */
	public HBTree() {
		this.objs = new HashSet<>();
	}
	
	@Override
	public IObject getObject(long objId) throws Exception{
		for (IObject obj : objs) {
			if (obj.hasId(objId)) {
				return obj;
			}
		}
		throw new HBObjectNotFoundException("Object not found in the design.");
	}

	@Override
	public boolean addObject(IObject newObj) throws Exception {
		if (containsObject(newObj.getPackage(), newObj.getName())) {
			throw new HBDuplicateObjectFoundException(
					ErrorMessages.DUPLICATE_OBJECT_FOUND, newObj.getName(),
					newObj.getPackage());
		}
		objs.add(newObj);
		return true;
	}

	@Override
	public boolean addRelationship(IObject from, IObject to, IRelationship rel)
			throws Exception {
		if (!containsObject(from.getId())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					from.getName());
		}
		if (!containsObject(to.getId())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					to.getName());
		}

		to.addRelationship(rel, from);
		return true;
	}

	@Override
	public boolean removeRelationship(IObject superType, IObject subType)
			throws Exception {
		if (!containsObject(superType.getId())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					superType.getName());
		}
		if (!containsObject(subType.getId())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					subType.getName());
		}
		subType.removeRelationship(superType);
		return true;
	}

	@Override
	public boolean removeObject(IObject rem) throws Exception {
		if (!containsObject(rem.getId())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					rem.getName());
		}

		objs.remove(rem);
		removeRelations(rem);
		return true;
	}

	@Override
	public void traverse(IHBTreeVisitor visitor) {
		// Get a new copy so we can sort
		List<IObject> sortedObjs = new ArrayList<>(objs);
		Collections.sort(sortedObjs, new Comparator<IObject>() {
			public int compare(IObject lhs, IObject rhs) {
				String lhsFull = lhs.getPackage() + lhs.getName();
				String rhsFull = rhs.getPackage() + rhs.getName();
				return lhsFull.compareTo(rhsFull);
			}
		});

		for (IObject obj : sortedObjs) {
			visitor.visit(obj, obj.getRelationships());
		}
	}

	// Removes any relationships containing the object rem. Returns whether
	// something was returned or not
	private void removeRelations(IObject rem) {
		for (IObject obj : objs) {
			if (obj.hasRelationship(rem)) {
				try {
					obj.removeRelationship(rem);
				} catch (Exception e) {
					// This is impossible due to the above check
				}
			}
		}
	}

	// This one is used when looking up objects
	private boolean containsObject(long id) {
		for (IObject obj : objs) {
			if (obj.hasId(id)) {
				return true;
			}
		}
		return false;
	}
	
	// This one is used when creating objects for the first time
	private boolean containsObject(String pkg, String name) {
		for (IObject obj : objs) {
			if (obj.getPackage().equals(pkg) && obj.hasName(name)) {
				return true;
			}
		}
		return false;
	}
	
	private Set<IObject> findAll(long objId, String pkg) {
		Set<IObject> ret = new HashSet<>();

		for (IObject obj : objs) {
			if (obj.getId() == (objId)
					&& (pkg == null || pkg != null
							&& obj.getPackage().equals(pkg))) {
				ret.add(obj);
			}
		}
		return ret;
	}
}
