package edu.iastate.cs362.hb.controller.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
	public boolean addInstanceField(String className, String fieldName,
			String fieldType, String... modifiers) throws Exception {
		return system.addInstanceField(className, fieldName, fieldType,
				modifiers);
	}

	@Override
	public boolean addRelationship(String fromObj, String toObj, String rel)
			throws Exception {
		return system.addRelationship(fromObj, toObj, rel);
	}

	@Override
	public boolean addPackage(String pkgName, String objName) throws Exception {
		return system.addPackage(pkgName, objName);
	}

	@Override
	public boolean addInstanceMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception {
		return system.addInstanceMethod(objName, methodName, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception {
		return system.addStaticMethod(objName, methodName, params, modifiers);
	}

	/**
	 * Calls system to remove a method
	 * 
	 * @throws HBMultipleObjectsFoundException
	 * @throws HBObjectNotFoundException
	 * @throws HBMethodNotFoundException
	 */
	@Override
	public boolean removeMethod(String objName, String methodName)
			throws Exception {
		return system.removeMethod(objName, methodName);

	}

	@Override
	public boolean removePackage(String objName) throws Exception {
		return system.removePackage(objName);
	}

	@Override
	public boolean removeRelationship(String superType, String subType)
			throws Exception {
		return system.removeRelationship(superType, subType);
	}

	@Override
	public boolean removeClass(String rem) throws Exception {
		return system.removeObj(rem);
	}

	@Override
	public boolean createClass(String name) throws Exception {
		return system.createClass(name);
	}

	@Override
	public boolean createInterface(String name) throws Exception {
		return system.createInterface(name);
	}

	@Override
	public List<IObjectBox> getMatchingObjects(String name) {
		return system.getMatchingObjects(name);
	}

	@Override
	public String listDesign() {
		return system.listDesign();
	}

	@Override
	public String listObject(String name) throws Exception {
		return system.listObject(name);
	}

	@Override
	public boolean changeName(String name, String newName) throws Exception {
		return system.changeName(name, newName);
	}

	@Override
	public boolean changePackage(String name, String pkgName) throws Exception {
		return system.changePackage(name, pkgName);
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
	public boolean importDesignXML(String path) throws Exception {
		return system.importDesignXML(path);
	}

	@Override
	public boolean importDesignJSON(String path) throws Exception {
		return system.importDesignJSON(path);
	}

	@Override
	public boolean importDesignSource(String path) throws Exception {
		return system.importDesignSource(path);
	}

	@Override
	public boolean changeModifiers(long objId, Set<String> modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeclassField(long objId, long fieldId, String type,
			Set<String> modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeClassMethod(long objId, long methodId,
			String methodName, List<String> methodArgs, Set<String> modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String listCachedMethod(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listCachedVariable(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long cacheMethod(String methodName, String returnType,
			Set<String> modifiers, List<String> arguments) {
		return system.cacheMethod(methodName, returnType, modifiers, arguments);
	}

	@Override
	public long cacheVariable(String name, String type, Set<String> modifiers) {
		return system.cacheVariable(name, type, modifiers);
	}

	@Override
	public String listCachedModifierSet(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long cacheModifierSet(Set<String> modifiers) {
		return system.cacheModifierSet(modifiers);
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return null;
	}
}
