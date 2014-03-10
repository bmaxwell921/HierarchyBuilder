package edu.iastate.cs362.hb.model.tree.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import edu.iastate.cs362.hb.constants.ErrorMessages;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTree;

/**
 * The tree structure for the Hierarchy Builder class
 * @author Brandon
 *
 */
public class HBTree implements IHBTree {
	
	/*
	 * Map of all the objects in the tree.
	 * Value is the set of all relationships where the
	 * key is the 'to' object.
	 * 
	 * I decided to sacrifice speed for clarity because it was getting
	 * out of hand trying to balance everything
	 */
	private Map<IObject, Set<Pair<IRelationship, IObject>>> objs;	
	
	/**
	 * Constructs a new, empty HBTree
	 */
	public HBTree() {
		this.objs = new HashMap<>();
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
		boolean remed = removeRelationFrom(rels, fromClass);
		if (!remed) {
			throw new HBRelationshipNotFoundException(ErrorMessages.RELATION_NOT_FOUND, fromClass.getName(), toClass.getName());
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
		removeRelations(toRemove);
		return true;
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
	
	/*
	 * Class holding a pair of two object
	 */
	private class Pair<T1, T2> {
		// First object
		public T1 fir;
		
		// Second object
		public T2 sec;
		
		// Creates a pair with the given items
		public Pair(T1 fir, T2 sec) {
			this.fir = fir;
			this.sec = sec;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((fir == null) ? 0 : fir.hashCode());
			result = prime * result + ((sec == null) ? 0 : sec.hashCode());
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
			Pair<?, ?> other = (Pair<?, ?>) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (fir == null) {
				if (other.fir != null)
					return false;
			} else if (!fir.equals(other.fir))
				return false;
			if (sec == null) {
				if (other.sec != null)
					return false;
			} else if (!sec.equals(other.sec))
				return false;
			return true;
		}

		private HBTree getOuterType() {
			return HBTree.this;
		}
		
		
	}
	
}
