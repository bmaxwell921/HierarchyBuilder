package edu.iastate.cs362.hb.controller;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;

/**
 *
 * Controller for the System
 * @author Brandon
 *
 */
public interface ISystemController {

	public boolean createDesign(String name);
	
	public boolean addInstanceField(String className, String iFieldName, String... modifiers) throws HBClassNotFoundException, HBObjectNotFoundException;
	
	public boolean addRelationship(String fromClass, String toClass, String relationship) throws HBObjectNotFoundException;
	
	public boolean addPackage(String packageName, String className);
	
	public boolean addInstanceMethod(String className, String methodName, String... modifiers);
	
	public boolean addStaticMethod(String className, String methodName, String... modifiers);
	
	public boolean removeMethod(String className, String methodName);
	
	public boolean removePackage(String className);
	
	public boolean removeRelationship(String fromString, String toString);
	
	public boolean removeClass(String toRemove);
	
	public boolean createClass(String name) throws HBDuplicateObjectFoundException;
	
	public boolean createInterface(String name) throws HBDuplicateObjectFoundException;
}
