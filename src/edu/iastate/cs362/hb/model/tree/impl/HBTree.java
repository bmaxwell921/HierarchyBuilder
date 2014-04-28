package edu.iastate.cs362.hb.model.tree.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.iastate.cs362.hb.constants.ErrorMessages;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.impl.HBObjectBox;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.ITree;
import edu.iastate.cs362.hb.model.tree.Pair;

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
	public IObject getObject(String objName) throws Exception {
		Set<IObject> matches = findAll(objName, null);
		if (matches.isEmpty()) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					objName);
		}
		if (matches.size() > 1) {
			throw new HBMultipleObjectsFoundException(
					ErrorMessages.MULT_OBJECT_WITH_NAME, objName);
		}

		return matches.iterator().next();
	}
	
	@Override
	public IObject getObject(long objId){
		return null;
	}

	@Override
	public boolean addObject(IObject newObj) throws Exception {
		if (containsObject(newObj.getName(), newObj.getPackage())) {
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
		if (!containsObject(from.getName(), from.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					from.getName());
		}
		if (!containsObject(to.getName(), to.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					to.getName());
		}

		to.addRelationship(rel, from);
		return true;
	}

	@Override
	public boolean removeRelationship(IObject from, IObject to)
			throws Exception {
		if (!containsObject(from.getName(), to.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					from.getName());
		}
		if (!containsObject(to.getName(), to.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					to.getName());
		}
		to.removeRelationship(from);
		return true;
	}

	@Override
	public boolean removeObject(IObject rem) throws Exception {
		if (!containsObject(rem.getName(), rem.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND,
					rem.getName());
		}

		objs.remove(rem);
		removeRelations(rem);
		return true;
	}

	@Override
	public List<IObjectBox> getMatchingObjects(String name) {
		List<IObjectBox> objs = new ArrayList<>();
		for (IObject obj : findAll(name, null)) {
			objs.add(new HBObjectBox(obj.getId(), obj.getName(), obj
					.getPackage()));
		}

		return objs;
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

	private boolean containsObject(String objName, String pkg) {
		return !findAll(objName, pkg).isEmpty();
	}

	// Returns a set of all the objects with the given name. Pass null for the
	// package to ignore it
	private Set<IObject> findAll(String objName, String pkg) {
		Set<IObject> ret = new HashSet<>();

		for (IObject obj : objs) {
			if (obj.getName().equals(objName)
					&& (pkg == null || pkg != null
							&& obj.getPackage().equals(pkg))) {
				ret.add(obj);
			}
		}
		return ret;
	}
}
