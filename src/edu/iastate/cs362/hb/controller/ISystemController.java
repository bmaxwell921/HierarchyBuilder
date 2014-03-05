package edu.iastate.cs362.hb.controller;

/**
 *
 * Controller for the System
 * @author Brandon
 *
 */
public interface ISystemController {

	public boolean createDesign(String name);
	
	public boolean addInstanceField(String className, String iFieldName, String... modifiers);
	
	public boolean addRelationship(String fromClass, String toClass, String relationship);
	
	public boolean addPackage(String packageName, String className);
	
	public boolean addInstanceMethod(String className, String methodName, String... modifiers);
	
	public boolean addStaticMethod(String className, String methodName, String... modifiers);
	
	public boolean removeMethod(String className, String methodName);
	
	public boolean removePackage(String className);
	
	public boolean removeRelationship(String fromString, String toString);
	
	public boolean removeClass(String toRemove);
	
	public boolean createClass(String name);
}
