package edu.iastate.cs362.hb.controller;

import java.util.List;

import edu.iastate.cs362.hb.model.IObjectBox;

/**
 * 
 * Controller for the System.
 * 
 * See the Javadoc on IDesignDoc
 * 
 * @author Brandon
 * 
 */
public interface ISystemController {

	public boolean createDesign(String name);

	public boolean addInstanceField(String className, String iFieldName,
			String... modifiers) throws Exception;

	public boolean addRelationship(String fromObj, String toObj, String rel)
			throws Exception;

	public boolean addPackage(String pkgName, String objName) throws Exception;

	public boolean addInstanceMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception;

	public boolean addStaticMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception;

	public boolean removeMethod(String objName, String methodName)
			throws Exception;

	public boolean removePackage(String objName) throws Exception;

	public boolean removeRelationship(String superType, String subType)
			throws Exception;

	public boolean removeClass(String rem) throws Exception;

	public boolean createClass(String name) throws Exception;

	public boolean createInterface(String name) throws Exception;

	public List<IObjectBox> getMatchingObjects(String name);

	public String listDesign();

	public String listObject(String name) throws Exception;

	public boolean changeName(String name, String newName) throws Exception;

	public boolean changePackage(String objName, String pkgName)
			throws Exception;

	// Added for iteration 2
	public boolean exportDesignXML(String path);

	public boolean exportDesignJSON(String path);

	public boolean exportDesignSource(String path);

	public boolean importDesignXML(String path) throws Exception;

	public boolean importDesignJSON(String path) throws Exception;

	public boolean importDesignSource(String path) throws Exception;

}
