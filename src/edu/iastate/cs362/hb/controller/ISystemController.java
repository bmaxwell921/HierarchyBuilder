package edu.iastate.cs362.hb.controller;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

/**
 * 
 * Controller for the System
 * 
 * @author Brandon
 * 
 */
public interface ISystemController {

	public boolean createDesign(String name);

	public boolean addInstanceField(String className, String iFieldName,
			String... modifiers) throws HBClassNotFoundException,
			HBObjectNotFoundException;

	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException;

	public boolean addPackage(String packageName, String className)
			throws HBObjectNotFoundException;

	public boolean addInstanceMethod(String className, String methodName,
			String params, String... modifiers)
			throws HBObjectNotFoundException, MalformattedCommandException,
			HBDuplicateMethodException;

	public boolean addStaticMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException;

	public boolean removeMethod(String className, String methodName);

	public boolean removePackage(String className);

	public boolean removeRelationship(String fromString, String toString);

	public boolean removeClass(String toRemove);

	public boolean createClass(String name)
			throws HBDuplicateObjectFoundException;

	public boolean createInterface(String name)
			throws HBDuplicateObjectFoundException;
}
