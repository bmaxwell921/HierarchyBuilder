package edu.iastate.cs362.hb.controller;

/**
 *
 * Controller for the System
 * @author Brandon
 *
 */
public interface ISystemController {

	/**
	 * Method used to create a new design
	 * @param name
	 * 			the name of the design
	 * @return
	 * 		true if the operation was successful, false otherwise
	 */
	public boolean createDesign(String name);
	
	/**
	 * Method used to add an instance field to the given class
	 * @param className
	 * 			the name of the class to add the instance field to
	 * @param iFieldName
	 * 			the name of the instance field to add
	 * @param modifiers
	 * 			all the modifiers for the instance field
	 * @return
	 * 			true if the operation was successful, false otherwise
	 */
	public boolean addInstanceField(String className, String iFieldName, String... modifiers);
	
	/**
	 * Adds the given relationship from the fromClass, to the toClass
	 * @param fromClass
	 * 				the class the relationship comes from
	 * @param toClass
	 * 				the class the relationship goes to
	 * @param relationship
	 * 				the type of relationship
	 * @return
	 * 			true if the operation was successful, false otherwise
	 */
	public boolean addRelationship(String fromClass, String toClass, String relationship);
	
	/**
	 * Adds the given package name to the given class
	 * @param packageNae
	 * 				the name of the package
	 * @param className
	 * 				the class to add the package to
	 * @return
	 * 			true if the operation was successful, false otherwise
	 */
	public boolean addPackage(String packageName, String className);
	
	/**
	 * Adds the given method with the given modifiers to the given class
	 * @param className
	 * 				the class to add the method to
	 * @param methodName
	 * 				the name of the method
	 * @param modifiers
	 * 				the modifiers to apply to the method
	 * @return
	 * 			true if the operation was successful, false otherwise
	 */
	public boolean addInstanceMethod(String className, String methodName, String... modifiers);
}
