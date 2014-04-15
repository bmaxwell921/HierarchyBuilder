package edu.iastate.cs362.hb.model.tree.impl;

import java.util.ArrayList;
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
import edu.iastate.cs362.hb.model.IManager;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.impl.HBObjectBox;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.ITree;
import edu.iastate.cs362.hb.model.tree.Pair;

/**
 * The tree structure for the Hierarchy Builder class
 * @author Brandon
 *
 */
public class HBTree implements ITree {
	
	/*
	 * Map of all the objects in the tree.
	 * Value is the set of all relationships where the
	 * key is the 'to' object.
	 * 
	 * I decided to sacrifice speed for clarity because it was getting
	 * out of hand trying to balance everything
	 */
	private Map<IObject, Set<Pair<IRelationship, IObject>>> objs;
	
	// All the objects that don't extend from or implement anything, used for exporting and listing
	private Set<IObject> roots;
	
	/**
	 * Constructs a new, empty HBTree
	 */
	public HBTree() {
		this.objs = new HashMap<>();
		roots = new HashSet<>();
	}
	
	@Override
	public IObject getObject(String className) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		Set<IObject> matches = findAll(className, null);
		if (matches.isEmpty()) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND, className);
		}
		if (matches.size() > 1) {
			throw new HBMultipleObjectsFoundException(ErrorMessages.MULT_OBJECT_WITH_NAME, className);
		}
		
		return matches.iterator().next();
	}

	@Override
	public boolean addObject(IObject newClass)
			throws HBDuplicateObjectFoundException {
		if (containsObject(newClass.getName(), newClass.getPackage())) {
			throw new HBDuplicateObjectFoundException(ErrorMessages.DUPLICATE_OBJECT_FOUND, newClass.getName(), newClass.getPackage());
		}
		
		// No relationships to start with 
		objs.put(newClass, null);
		roots.add(newClass);
		return true;
	}

	@Override
	public boolean addRelationship(IObject fromClass, IObject toClass,
			IRelationship relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException {
		if (!containsObject(fromClass.getName(), fromClass.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND, fromClass.getName());
		}	
		if (!containsObject(toClass.getName(), toClass.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND, toClass.getName());
		}
		if (relationExists(fromClass, toClass)) {
			throw new HBDuplicateRelationshipException(ErrorMessages.DUPLICATE_RELATION, fromClass.getName(), toClass.getName());
		}
		
		// The values in objs are the froms in a relationship
		Set<Pair<IRelationship, IObject>> rel = objs.get(toClass);
		if (rel == null) {
			rel = new HashSet<>();
		}
		rel.add(new Pair<>(relationship, fromClass));
		// Put it back in case it was null before
		objs.put(toClass, rel);
		
		// Remove the toClass from roots since it's no longer a root
		roots.remove(toClass);
		return true;
	}

	@Override
	public boolean removeRelationship(IObject fromClass, IObject toClass)
			throws HBObjectNotFoundException, HBRelationshipNotFoundException {
		if (!containsObject(fromClass.getName(), toClass.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND, fromClass.getName());
		}
		if (!containsObject(toClass.getName(), toClass.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND, toClass.getName());
		}
		Set<Pair<IRelationship, IObject>> rels = objs.get(toClass);
		if(rels == null){
			throw new HBRelationshipNotFoundException(ErrorMessages.RELATION_NOT_FOUND, fromClass.getName(), toClass.getName());
		}
		boolean remed = removeRelationFrom(rels, fromClass);
		if (!remed) {
			throw new HBRelationshipNotFoundException(ErrorMessages.RELATION_NOT_FOUND, fromClass.getName(), toClass.getName());
		}
		
		// If we've removed all the relationships re-add toClass to roots
		if (rels.isEmpty()) {
			roots.add(toClass);
		}
		return true;
	}

	@Override
	public boolean removeObject(IObject toRemove)
			throws HBObjectNotFoundException {
		if (!containsObject(toRemove.getName(), toRemove.getPackage())) {
			throw new HBObjectNotFoundException(ErrorMessages.OBJECT_NOT_FOUND, toRemove.getName());
		}
		
		objs.remove(toRemove);
		roots.remove(toRemove);
		removeRelations(toRemove);
		return true;
	}
	
	@Override
	public List<IObjectBox> getMatchingObjects(String name) {
		List<IObjectBox> objs = new ArrayList<>();
		for (IObject obj : findAll(name, null)) {
			objs.add(new HBObjectBox(obj.getId(), obj.getName(), obj.getPackage()));
		}
		
		return objs;
	}
	
	@Override
	public void traverse(IHBTreeVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isRoot(IObject o) {
		return roots.contains(o);
	}
	
	// Removes any relationships containing the object rem. Returns whether something was returned or not
	private boolean removeRelations(IObject rem) {
		boolean remed = false;
		for (IObject obj : objs.keySet()) {
			Set<Pair<IRelationship, IObject>> rel = objs.get(obj);
			if (rel == null) {
				continue;
			}
			if (removeRelationFrom(rel, rem)) {
				remed = true;
			}
		}
		return remed;
	}
	
	private boolean removeRelationFrom(Set<Pair<IRelationship, IObject>> rels, IObject from) {
		boolean remed = false;
		for (Iterator<Pair<IRelationship, IObject>> iter = rels.iterator(); iter.hasNext(); ) {
			Pair<IRelationship, IObject> pair = iter.next();
			if (pair.sec.equals(from)) {
				iter.remove();
				remed = true;
			}
		}
		return remed;
	}
	
	private boolean containsObject(String objName, String pkg) {
		return !findAll(objName, pkg).isEmpty();
	}
	
	// checks whether a relation exists between the two objects
	private boolean relationExists(IObject from, IObject to) {
		Set<Pair<IRelationship, IObject>> relations = objs.get(to);
		if (relations == null) {
			return false;
		}
		
		for (Pair<IRelationship, IObject> rel : relations) {
			if (rel.sec.equals(from)) {
				return true;
			}
		}
		return false;
	}
	
	// Returns a set of all the objects with the given name. Pass null for the package to ignore it
	private Set<IObject> findAll(String objName, String pkg) {
		Set<IObject> ret = new HashSet<>();
		
		for (IObject obj : objs.keySet()) {
			if (obj.getName().equals(objName) && (pkg == null || pkg != null && obj.getPackage().equals(pkg))) {
				ret.add(obj);
			}
		}	
		return ret;
	}
}
