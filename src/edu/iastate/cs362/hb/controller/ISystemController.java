package edu.iastate.cs362.hb.controller;

import java.util.List;
import java.util.Set;

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

	public boolean addInstanceField(long objId, String fieldName,
			String fieldType, String... modifiers) throws Exception;

	public boolean addRelationship(long superId, long subId, String rel)
			throws Exception;

	public boolean addPackage(long objId, String pkgName) throws Exception;

	public boolean addObject(String type, Object obj) throws Exception;

	public boolean addInstanceMethod(long objId, String methodName, String returnType,
			String params, String... modifiers) throws Exception;

	public boolean addStaticMethod(long objId, String methodName, String returnType,
			String params, String... modifiers) throws Exception;

	public boolean removeMethod(long objId, String methodName)
			throws Exception;

	public boolean removePackage(long objId) throws Exception;

	public boolean removeRelationship(long superId, long subId)
			throws Exception;

	public boolean removeClass(long remId) throws Exception;

	public boolean createClass(String name) throws Exception;

	public boolean createInterface(String name) throws Exception;

	public String listDesign();

	public String listObject(long objId) throws Exception;

	public boolean changeName(long objId, String newName) throws Exception;

	public boolean changePackage(long objId, String pkgName)
			throws Exception;

	// Added for iteration 2
	public boolean exportDesignXML(String path);

	public boolean exportDesignJSON(String path);

	public boolean exportDesignSource(String path);

	public boolean importDesignXML(String path) throws Exception;

	public boolean importDesignJSON(String path) throws Exception;

	public boolean importDesignSource(String path) throws Exception;

	// Added for iteration 3
	public boolean changeModifiers(long objId, Set<String> modifiers)
			throws Exception;

	public boolean changeClassField(long objId, long fieldId, String fieldName,
			String type, Set<String> modifiers) throws Exception;

	public boolean changeClassMethod(long objId, long methodId,
			String methodName, String returnType, List<String> methodArgs, Set<String> modifiers)
			throws Exception;

	public String listCachedMethod(long id);

	public String listCachedVariable(long id);

	public long cacheMethod(String methodName, String returnType,
			Set<String> modifiers, List<String> arguments);

	public long cacheVariable(String name, String type, Set<String> modifiers);

	public String listCachedModifierSet(long id);

	public long cacheModifierSet(Set<String> modifiers);

	public String showHelp();
}