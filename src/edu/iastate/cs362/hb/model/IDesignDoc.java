package edu.iastate.cs362.hb.model;

import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMethodNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.model.attributes.Nameable;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;

/**
 * Interface used to define behavior for the HBDesignDoc object
 * 
 * @author Brandon
 * 
 */
public interface IDesignDoc extends Nameable {

	/**
	 * Creates a new instance field with the given name and modifiers in the
	 * given class
	 * 
	 * @param objId
	 * @param fieldName
	 * @param fieldType
	 * @param modifiers
	 * @return
	 * @throws HBClassNotFoundException
	 *             If there is no class in the design doc with the given name
	 * @throws HBObjectNotFoundException
	 *             If there is no class in the design doc with the given name
	 * @throws HBMultipleObjectsFoundException
	 *             If there are multiple classes in the design doc with the
	 *             given name
	 */
	public boolean addInstanceField(long objId, String fieldName, String fieldType,
			String... modifiers) throws Exception;

	/**
	 * Creates a new relationship from super to sub
	 * 
	 * @param superId
	 * @param subId
	 * @param rel
	 * @return
	 * @throws HBObjectNotFoundException
	 *             If either superType or subType aren't in the design doc
	 * @throws HBDuplicateRelationshipException
	 *             If a relationship already exists between superType and
	 *             subType
	 * @throws HBMultipleObjectsFoundException
	 *             If there are multiple objects with the name superType or
	 *             subType
	 */
	public boolean addRelationship(long superId, long subId, String rel)
			throws Exception;

	/**
	 * Adds the given packageName to the given object
	 * 
	 * @param objId
	 * @param objName
	 * @return
	 * @throws HBObjectNotFoundException
	 *             If obj doesn't exist in the design doc
	 * @throws HBMultipleObjectsFoundException
	 *             If multiple objects with the given name exist in the design
	 *             doc
	 */
	public boolean addPackage(long objId, String pkg) throws Exception;

	/**
	 * Creates a new instance method in the given class with the given
	 * information
	 * 
	 * @param objId
	 * @param methodName
	 * @param params
	 * @param modifiers
	 * @return
	 * @throws HBObjectNotFoundException
	 *             If there isn't an object with the given name in the design
	 * @throws HBDuplicateMethodException
	 *             If a duplicate copy of the given method exists in the given
	 *             object
	 * @throws HBMultipleObjectsFoundException
	 *             If multiple objects with the given name exist in the design
	 *             doc
	 */
	public boolean addInstanceMethod(long objId, String methodName, String returnType,
			String params, String... modifiers) throws Exception;

	/**
	 * Creates a new static method in the given class with the given information
	 * 
	 * @param objId
	 * @param methodName
	 * @param params
	 * @param modifiers
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBDuplicateMethodException
	 * @throws HBMultipleObjectsFoundException
	 */
	public boolean addStaticMethod(long objId, String methodName, String returnType,
			String params, String... modifiers) throws Exception;

	/**
	 * Removes the method with the given name from the given class
	 * 
	 * @param objId
	 * @param methodName
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException
	 * @throws HBMethodNotFoundException
	 */
	public boolean removeMethod(long objId, String methodName)
			throws Exception;

	/**
	 * Removes the current package name from the given object
	 * 
	 * @param objId
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException
	 */
	public boolean removePackage(long objId) throws Exception;

	/**
	 * Removes the relationship between the two given objects
	 * 
	 * @param superId
	 * @param subId
	 * @return
	 * @throws HBMultipleObjectsFoundException
	 * @throws HBObjectNotFoundException
	 * @throws HBRelationshipNotFoundException
	 */
	public boolean removeRelationship(long superId, long subId)
			throws Exception;

	/**
	 * Removes the object with the given name from this design doc
	 * 
	 * @param remId
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException
	 */
	public boolean removeObj(long remId) throws Exception;

	/**
	 * Creates a new class with the given name
	 * 
	 * @param name
	 * @return
	 * @throws HBDuplicateObjectFoundException
	 */
	public boolean createClass(String name) throws Exception;

	/**
	 * Creates a new interface with the given name
	 * 
	 * @param name
	 * @return
	 * @throws HBDuplicateObjectFoundException
	 */
	public boolean createInterface(String name) throws Exception;

	/**
	 * Method used to visit all objects in the design
	 * 
	 * @param visitor
	 */
	public void traverse(IHBTreeVisitor visitor);

	/**
	 * Returns the string representation of this design doc
	 * 
	 * @return
	 */
	public String list();

	/**
	 * Method used to get the string representation of a single object in the
	 * design
	 * 
	 * @param objId
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException
	 */
	public String listObject(long objId) throws Exception;

	/**
	 * Method used to change the name of the given object
	 * 
	 * @param objId
	 * @param newName
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException
	 */
	public boolean changeName(long objId, String newName) throws Exception;

	/**
	 * Method used to change the package of the given class
	 * 
	 * @param objId
	 * @param pkgName
	 * @return
	 * @throws HBObjectNotFoundException
	 * @throws HBMultipleObjectsFoundException
	 */
	public boolean changePackage(long objId, String pkgName) throws Exception;

	/**
	 * Method used to add the given interfaces and classes to the design
	 * 
	 * @param interfaces
	 * @param classes
	 */
	public void addAllInner(Set<? extends IObject> interfaces,
			Set<? extends IObject> classes);
	/**
	 * 
	 * @param objId
	 * @param modifiers
	 * @return
	 * @throws Exception
	 */
	public boolean changeModifiers(long objId, Set<String> modifiers) throws Exception;
	
	/**
	 * 
	 * @param objId
	 * @param fieldId
	 * @param type
	 * @param modifiers
	 * @return 
	 * @throws Exception
	 */
	public boolean changeClassField(long objId, long fieldId, String fieldName, String type, Set<String> modifiers) throws Exception;
	
	/**
	 * 
	 * @param objId
	 * @param methodId
	 * @param methodName
	 * @param returnType 
	 * @param methodArgs
	 * @param modifiers
	 * @return
	 * @throws Exception 
	 */
	public boolean changeClassMethod(long objId, long methodId, String methodName, String returnType, List<String> methodArgs, Set<String> modifiers) throws Exception;

	public boolean removeField(long id, String name) throws Exception;
}
