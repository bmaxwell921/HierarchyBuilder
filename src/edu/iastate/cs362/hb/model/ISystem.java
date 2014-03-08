package edu.iastate.cs362.hb.model;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

/**
 * Interface which defines the behavior for System objects
 * @author Brandon
 *
 */
public interface ISystem {
	
	public boolean createDesign(String name);
	
	public boolean addInstanceField(String className, String instanceFieldName, String...modifiers) throws HBClassNotFoundException, HBObjectNotFoundException;
	
	public boolean addRelationship(String fromClass, String toClass, String relationship) throws HBObjectNotFoundException;
	
	public boolean addPackage(String packageName, String className) throws HBObjectNotFoundException;
	
	public boolean addInstanceMethod(String className, String methodName, String params, String...modifiers) throws HBObjectNotFoundException, MalformattedCommandException, HBDuplicateMethodException;
	
	public boolean addStaticMethod(String className, String methodName, String...modifiers);
	
	public boolean removeMethod(String className, String methodName);
	
	public boolean removePackage(String className);
	
	public boolean removeRelationship(String fromClass, String toClass);
	
	public boolean removeClass(String className);
	
	public boolean createClass(String name) throws HBDuplicateObjectFoundException;
	
	public boolean createInterface(String name) throws HBDuplicateObjectFoundException;

}
