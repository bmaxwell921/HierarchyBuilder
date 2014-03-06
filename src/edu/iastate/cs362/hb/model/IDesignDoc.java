package edu.iastate.cs362.hb.model;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;

/**
 * Interface used to define behavior for the DesignDoc object
 * @author Brandon
 *
 */
public interface IDesignDoc {
	
	public boolean addInstanceField(String className, String instanceFieldName, String...modifiers) throws HBClassNotFoundException;
	
	public IInstanceField createInstanceField(String name, String...modifiers);
	
	public boolean addRelationship(String fromClass, String toClass, String relationship);
	
	public boolean addPackage(String packageName, String className);
	
	public boolean addInstanceMethod(String className, String methodName, String...modifiers);
	
	public boolean addStaticMethod(String className, String methodName, String...modifiers);
	
	public boolean removeMethod(String className, String methodName);
	
	public boolean removePackage(String className);
	
	public boolean removeRelationship(String fromClass, String toClass);
	
	public boolean removeClass(String toRemove);
	
	public IClass createClass(String name);
}
