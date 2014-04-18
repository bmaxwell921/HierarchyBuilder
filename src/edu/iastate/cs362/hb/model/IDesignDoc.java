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
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.model.attributes.Nameable;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.impl.HBInterface;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;

/**
 * Interface used to define behavior for the HBDesignDoc object
 * 
 * @author Brandon
 * 
 */
public interface IDesignDoc extends Nameable {

	public boolean addInstanceField(String className, String instanceFieldName,
			String... modifiers) throws HBClassNotFoundException,
			HBObjectNotFoundException, HBMultipleObjectsFoundException;

	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException, HBMultipleObjectsFoundException;

	public boolean addPackage(String packageName, String className)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException;

	public boolean addInstanceMethod(String className, String methodName,
			String params, String... modifiers)
			throws HBObjectNotFoundException, MalformattedCommandException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException;

	public boolean addStaticMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException;

	public boolean removeMethod(String className, String methodName)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException,
			HBMethodNotFoundException;

	public boolean removePackage(String className)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException;

	public boolean removeRelationship(String fromClass, String toClass)
			throws HBMultipleObjectsFoundException, HBObjectNotFoundException,
			HBRelationshipNotFoundException;

	public boolean removeClass(String toRemove)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException;

	public boolean createClass(String name)
			throws HBDuplicateObjectFoundException;

	public boolean createInterface(String name)
			throws HBDuplicateObjectFoundException;

	public void traverse(IHBTreeVisitor visitor);

	public List<IObjectBox> getMatchingObjects(String name);

	public String list();

	public String listObject(String name) throws HBObjectNotFoundException,
			HBMultipleObjectsFoundException;

	public boolean changeName(String name, String newName)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException;

	public boolean changePackage(String name, String packageName)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException;

	/**
	 * Method used to add the given interfaces and classes to the design
	 * 
	 * @param interfaces
	 * @param classes
	 */
	public void addAllInner(Set<? extends IObject> interfaces,
			Set<? extends IObject> classes);
}
