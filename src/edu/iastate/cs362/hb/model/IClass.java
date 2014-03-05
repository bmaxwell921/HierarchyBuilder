package edu.iastate.cs362.hb.model;

/**
 * Interface representing a Class in a Class Hierarchy
 * @author Brandon
 *
 */
public interface IClass extends IObject {

	public IClass create();
	
	public boolean addInstanceMethod(String methodName, String...modifiers);
	
	public boolean addStaticMethod(String methodName, String...modifiers);
	
	public boolean removeMethod(String methodName);
}
