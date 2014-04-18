package edu.iastate.cs362.hb.controller.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import edu.iastate.cs362.hb.controller.ISystemController;
import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMethodNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.exceptions.MalformattedInputException;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.ISystem;

/**
 * A concrete implementation of the ISystemController interface
 * 
 * @author Alex, Brandon
 * 
 */
public class SystemController implements ISystemController {

	// The system to interact with
	private ISystem system;

	/**
	 * Constructus the SystemController for the given System
	 * 
	 * @param system
	 */
	public SystemController(ISystem system) {
		this.system = system;
	}

	@Override
	public boolean createDesign(String name) {
		return system.createDesign(name);
	}

	/**
	 * addInstanceField(String className, String iFieldName, String...
	 * modifiers) calls addInstanceField in System
	 * 
	 * @param className
	 *            a String for the class name instance field is a part of
	 * @param iFieldName
	 *            a String for the instance field's name
	 * @param modifiers
	 *            Strings to tell what modifiers the Instance Field should have
	 * @return a boolean returning the success (true) or failure of the instance
	 *         field addition
	 * @throws HBClassNotFoundException
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException 
	 */
	@Override
	public boolean addInstanceField(String className, String iFieldName,
			String... modifiers) throws HBClassNotFoundException,
			HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return system.addInstanceField(className, iFieldName, modifiers);
	}

	@Override
	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException, HBMultipleObjectsFoundException {
		return system.addRelationship(fromClass, toClass, relationship);
	}

	@Override
	public boolean addPackage(String packageName, String className)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return system.addPackage(packageName, className);
	}

	@Override
	public boolean addInstanceMethod(String className, String methodName,
			String params, String... modifiers)
			throws HBObjectNotFoundException, MalformattedCommandException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException {
		return system.addInstanceMethod(className, methodName, params,
				modifiers);
	}

	@Override
	public boolean addStaticMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException {
		return system.addStaticMethod(className, methodName, params, modifiers);
	}

	/**
	 * Calls system to remove a method
	 * @throws HBMultipleObjectsFoundException 
	 * @throws HBObjectNotFoundException 
	 * @throws HBMethodNotFoundException 
	 */
	@Override
	public boolean removeMethod(String className, String methodName) throws HBObjectNotFoundException, HBMultipleObjectsFoundException, HBMethodNotFoundException {
		return system.removeMethod(className, methodName);
		
	}

	@Override
	public boolean removePackage(String className) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return system.removePackage(className);
	}

	@Override
	public boolean removeRelationship(String fromString, String toString) throws HBMultipleObjectsFoundException, HBObjectNotFoundException, HBRelationshipNotFoundException {
		return system.removeRelationship(fromString, toString);
	}

	@Override
	public boolean removeClass(String toRemove) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return system.removeClass(toRemove);
	}

	@Override
	public boolean createClass(String name)
			throws HBDuplicateObjectFoundException {
		return system.createClass(name);
	}

	@Override
	public boolean createInterface(String name)
			throws HBDuplicateObjectFoundException {
		return system.createInterface(name);
	}

	@Override
	public List<IObjectBox> getMatchingObjects(String name) {
		return system.getMatchingObjects(name);
	}
	
	@Override
	public String listDesign(){
		return system.listDesign();
	}
	
	@Override
	public String listObject(String name) throws HBObjectNotFoundException, HBMultipleObjectsFoundException{
		return system.listObject(name);
	}
	
	@Override
	public boolean changeName(String name, String newName) throws HBObjectNotFoundException, HBMultipleObjectsFoundException{
		return system.changeName(name, newName);
	}
	
	@Override
	public boolean changePackage(String name, String packageName) throws HBObjectNotFoundException, HBMultipleObjectsFoundException{
		return system.changePackage(name, packageName);
	}

	@Override
	public boolean exportDesignXML(String path) {
		return system.exportDesignXML(path);
	}

	@Override
	public boolean exportDesignJSON(String path) {
		return system.exportDesignJSON(path);
	}

	@Override
	public boolean exportDesignSource(String path) {
		return system.exportDesignSource(path);
	}

	@Override
	public boolean importDesignXML(String path) throws FileNotFoundException, IOException, MalformattedInputException {
		return system.importDesignXML(path);
	}

	@Override
	public boolean importDesignJSON(String path) throws FileNotFoundException, IOException, MalformattedInputException {
		return system.importDesignJSON(path);
	}

	@Override
	public boolean importDesignSource(String path) throws FileNotFoundException, IOException, MalformattedInputException {
		return system.importDesignSource(path);
	}
}
