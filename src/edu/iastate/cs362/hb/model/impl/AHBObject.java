package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.constants.ErrorMessages;
import edu.iastate.cs362.hb.constants.ObjectConstants;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMethodNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.main.IdManager;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.Pair;

/**
 * Class holding some common implementation between interfaces and classes. This
 * probably doesn't need to be abstract unless something actually needs to go
 * into HBInterface
 * 
 * @author Brandon
 * 
 */
public abstract class AHBObject implements IObject {

	// Id of this object
	private long id;

	// This object's package
	private String pkg;

	// This object's name
	private String name;

	// All the modifiers. TODO this should probably have some notion of order
	private Set<String> modifiers;

	protected List<IMethod> methods;

	protected Set<Pair<IRelationship, IObject>> relationships;

	/**
	 * Creates a new AHBObject with the given name that goes in the default
	 * package
	 * 
	 * @param name
	 */
	public AHBObject(String name) {
		this.name = name;
		this.pkg = ObjectConstants.DEFAULT_PKG;
		this.modifiers = new HashSet<>();
		this.methods = new ArrayList<>();
		this.relationships = new HashSet<>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name != null && this.name.equals(name);
	}

	@Override
	public boolean changeName(String newName) {
		this.name = newName;
		return true;
	}

	@Override
	public boolean changePackage(String packageName) {
		this.pkg = packageName;
		return true;
	}

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean hasId(long id) {
		return this.id == id;
	}

	@Override
	public boolean addPackage(String packageName) {
		this.pkg = packageName;
		return true;
	}

	@Override
	public boolean removePackage() {
		this.pkg = ObjectConstants.DEFAULT_PKG;
		return true;
	}

	@Override
	public String getPackage() {
		return pkg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pkg == null) ? 0 : pkg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		// Apparently this is bad style, but we need interfaces and classes with
		// the same name and package to be equal
		if (getClass().getSuperclass() != obj.getClass().getSuperclass())
			return false;
		AHBObject other = (AHBObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pkg == null) {
			if (other.pkg != null)
				return false;
		} else if (!pkg.equals(other.pkg))
			return false;
		return true;
	}

	@Override
	public void addModifiers(String... modifier) {
		modifiers.addAll(Arrays.asList(modifier));
	}

	@Override
	public void addModifiers(Set<String> modifiers){
		this.modifiers.addAll(modifiers);
	}
	
	@Override
	public boolean removeModifier(String modifier) {
		return modifiers.remove(modifier);
	}

	@Override
	public Set<String> getModifiers() {
		return this.modifiers;
	}

	@Override
	public boolean addMethod(IMethod method) throws HBDuplicateMethodException {
		if (methods.contains(method)) {
			throw new HBDuplicateMethodException(
					ErrorMessages.DUPLICATE_METHOD, this.name, method.getName());
		}
		IdManager.getInstance().registerObject(method, method.getName());
		methods.add(method);
		return true;
	}

	@Override
	public boolean removeMethod(IMethod method) {
		return methods.remove(method);
	}

	@Override
	public IMethod getMethod(String name) throws HBMethodNotFoundException {
		for (IMethod meth : methods) {
			if (meth.hasName(name)) {
				return meth;
			}
		}
		throw new HBMethodNotFoundException(ErrorMessages.METHOD_NOT_FOUND,
				name);
	}

	@Override
	public boolean addRelationship(IRelationship rel, IObject fromObj)
			throws Exception {
		for (Pair<IRelationship, IObject> pair : relationships) {
			if (pair.sec.equals(fromObj)) {
				throw new HBDuplicateRelationshipException(
						ErrorMessages.DUPLICATE_RELATION, fromObj.getName(),
						this.getName());
			}
		}

		relationships.add(new Pair<>(rel, fromObj));

		return true;
	}

	@Override
	public boolean removeRelationship(IObject fromObj) throws Exception {
		for (Iterator<Pair<IRelationship, IObject>> iter = relationships
				.iterator(); iter.hasNext();) {
			Pair<IRelationship, IObject> pair = iter.next();
			if (pair.sec.equals(fromObj)) {
				iter.remove();
				return true;
			}
		}

		throw new HBRelationshipNotFoundException(
				ErrorMessages.RELATION_NOT_FOUND, fromObj.getName(),
				this.getName());
	}

	@Override
	public boolean hasRelationship(IObject from) {
		for (Pair<IRelationship, IObject> pair : relationships) {
			if (pair.sec.equals(from)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Pair<IRelationship, IObject>> getRelationships() {
		return relationships;
	}

	@Override
	public String toString() {
		return pkg + "." + name;
	}

	public int getNumMethods() {
		return methods.size();
	}

	@Override
	abstract public String list();
	
	@Override
	public boolean changeModifiers(Set<String> modifiers){
		this.modifiers.removeAll(modifiers);
		return this.modifiers.addAll(modifiers);
	}
	
	@Override
	public boolean changeClassMethod(long methodId, IMethod m, List<String> args){
		for(int i = 0; i < methods.size(); i++){
			IMethod toCheck = methods.get(i);
			if(toCheck.getId() == methodId){
				/*toCheck = m;
				return true;*/
				return toCheck.update(m, args);
			}
		}
		return true;
	}
}
