package edu.iastate.cs362.hb.model;

/**
 * Interface used to define behavior for the DesignDoc object
 * @author Brandon
 *
 */
public interface IDesignDoc {

	public IDesignDoc create();
	
	public boolean addInstanceField(String className, String instanceFieldName, String...modifiers);
	
	public IInstanceField createInstanceField(String name, String...modifiers);
	
	public IInstanceField addInstanceField(IClass iClass, IInstanceField instanceField);
	
	public boolean addRelationship(String fromClass, String toClass, String relationship);
	
	public boolean addPackage(String packageName, String className);
	
	public boolean addInstanceMethod(String className, String methodName, String...modifiers);
	
	public boolean addStaticMethod(String className, String methodName, String...modifiers);
	
	public boolean removeMethod(String className, String methodName);
	
	public boolean removePackage(String className);
	
	public boolean removeRelationship(String fromClass, String toClass);
	
	public boolean removeClass(String toRemove);
	
	public boolean createClass(String name);
}
