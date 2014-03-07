package edu.iastate.cs362.hb.model;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.attributes.Nameable;

/**
 * Interface used to define behavior for the DesignDoc object
 * @author Brandon
 *
 */
public interface IDesignDoc extends Nameable {
	
	public boolean addInstanceField(String className, String instanceFieldName, String...modifiers) throws HBClassNotFoundException, HBObjectNotFoundException;
	
	public IInstanceField createInstanceField(String name, String...modifiers);
	
	public boolean addRelationship(String fromClass, String toClass, String relationship) throws HBObjectNotFoundException;
	
	public boolean addPackage(String packageName, String className) throws HBObjectNotFoundException;
	
	public boolean addInstanceMethod(String className, String methodName, String...modifiers);
	
	public boolean addStaticMethod(String className, String methodName, String...modifiers);
	
	public boolean removeMethod(String className, String methodName);
	
	public boolean removePackage(String className);
	
	public boolean removeRelationship(String fromClass, String toClass);
	
	public boolean removeClass(String toRemove);
	
	public boolean createClass(String name) throws HBDuplicateObjectFoundException;
	
	public boolean createInterface(String name) throws HBDuplicateObjectFoundException;
}
