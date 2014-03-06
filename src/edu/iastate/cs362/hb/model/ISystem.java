package edu.iastate.cs362.hb.model;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;

/**
 * Interface which defines the behavior for System objects
 * @author Brandon
 *
 */
public interface ISystem {
	
	public boolean createDesign(String name);
	
	public boolean addInstanceField(String className, String instanceFieldName, String...modifiers) throws HBClassNotFoundException;
	
	public boolean addRelationship(String fromClass, String toClass, String relationship);
	
	public boolean addPackage(String packageName, String className);
	
	public boolean addInstanceMethod(String className, String methodName, String...modifiers);
	
	public boolean addStaticMethod(String className, String methodName, String...modifiers);
	
	public boolean removeMethod(String className, String methodName);
	
	public boolean removePackage(String className);
	
	public boolean removeRelationship(String fromClass, String toClass);
	
	public boolean removeClass(String className);
	
	public boolean createClass(String name);

}
