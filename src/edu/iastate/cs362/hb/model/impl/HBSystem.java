package edu.iastate.cs362.hb.model.impl;

import java.util.List;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.ISystem;

public class HBSystem implements ISystem {

	private IDesignDoc doc;

	@Override
	public boolean createDesign(String name) {
		// TODO save the old one?
		doc = new HBDesignDoc(name);
		return true;
	}

	/**
	 * addInstanceField(String className, String iFieldName, String...
	 * modifiers) calls addInstanceField in HBDesignDoc
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
	public boolean addInstanceField(String className, String instanceFieldName,
			String... modifiers) throws HBClassNotFoundException,
			HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return doc.addInstanceField(className, instanceFieldName, modifiers);
	}

	@Override
	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException, HBMultipleObjectsFoundException {
		return doc.addRelationship(fromClass, toClass, relationship);
	}

	@Override
	public boolean addPackage(String packageName, String className)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return doc.addPackage(packageName, className);
	}

	@Override
	public boolean addInstanceMethod(String className, String methodName,
			String params, String... modifiers)
			throws HBObjectNotFoundException, MalformattedCommandException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException {
		return doc.addInstanceMethod(className, methodName, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException {
		return doc.addStaticMethod(className, methodName, params, modifiers);
	}

	/**
	 * @throws HBMultipleObjectsFoundException 
	 * @throws HBObjectNotFoundException 
	 * 
	 */
	@Override
	public boolean removeMethod(String className, String methodName) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return doc.removeMethod(className, methodName);
	}

	@Override
	public boolean removePackage(String className) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return doc.removePackage(className);
	}

	@Override
	public boolean removeRelationship(String fromClass, String toClass) throws HBMultipleObjectsFoundException, HBObjectNotFoundException, HBRelationshipNotFoundException {
		return doc.removeRelationship(fromClass, toClass);
	}

	@Override
	public boolean removeClass(String className) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return doc.removeClass(className);
	}

	@Override
	public boolean createClass(String name)
			throws HBDuplicateObjectFoundException {
		return doc.createClass(name);
	}

	@Override
	public boolean createInterface(String name)
			throws HBDuplicateObjectFoundException {
		return doc.createInterface(name);
	}

	@Override
	public List<IObjectBox> getMatchingObjects(String name) {
		return doc.getMatchingObjects(name);
	}

}
