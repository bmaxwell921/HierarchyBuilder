package edu.iastate.cs362.hb.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.exceptions.MalformattedInputException;

/**
 * Interface which defines the behavior for System objects.
 * 
 * See javadoc in the DesignDoc class for details
 * 
 * @author Brandon
 * 
 */
public interface ISystem {

	public boolean createDesign(String name);

	public boolean addInstanceField(String className, String fieldName,
			String fieldType, String... modifiers) throws Exception;

	public boolean addRelationship(long superId, long subId, String rel)
			throws Exception;

	public boolean addPackage(String pkgName, long objId) throws Exception;

	public boolean addInstanceMethod(long objId, String methodName,
			String params, String... modifiers) throws Exception;

	public boolean addStaticMethod(long objId, String methodName,
			String params, String... modifiers) throws Exception;

	public boolean removeMethod(String objName, String methodName)
			throws Exception;

	public boolean removePackage(String objName) throws Exception;

	public boolean removeRelationship(String superType, String subType)
			throws Exception;

	public boolean removeObj(long remId) throws Exception;

	public boolean createClass(String name) throws Exception;

	public boolean createInterface(String name) throws Exception;

	public List<IObjectBox> getMatchingObjects(String name);

	public String listDesign();

	public String listObject(String name) throws Exception;

	public boolean changeName(String name, String newName) throws Exception;

	public boolean changePackage(String name, String packageName)
			throws Exception;

	// Added for iteration 2
	public boolean exportDesignXML(String path);

	public boolean exportDesignJSON(String path);

	public boolean exportDesignSource(String path);

	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MalformattedInputException
	 */
	public boolean importDesignXML(String path) throws Exception;

	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MalformattedInputException
	 */
	public boolean importDesignJSON(String path) throws Exception;

	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MalformattedInputException
	 */
	public boolean importDesignSource(String path) throws Exception;
	
	//Iteration 3
	public boolean changeModifiers(long objId, Set<String> modifiers) throws Exception;
	
	public boolean changeClassField(long objId, long fieldId, String fieldName, String type, Set<String> modifiers) throws Exception;
	
	public boolean changeClassMethod(long objId, long methodId, String methodName, List<String> methodArgs, Set<String> modifiers) throws Exception;
	
	public String listCachedMethod(long id);
	
	public String listCachedVariable(long id);
	
	public long cacheMethod(String methodName, String returnType, Set<String> modifiers, List<String> arguments);
	
	public long cacheVariable(String name, String type, Set<String> modifiers);
	
	public String listCachedModifierSet(long id);
	
	public long cacheModifierSet(Set<String> modifiers);
	
	public String showHelp();
	
	public boolean addObject(String type, Object obj) throws Exception;
}