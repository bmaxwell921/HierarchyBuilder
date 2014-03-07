package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.ISystem;

public class HBSystem implements ISystem{
	
	private IDesignDoc doc;

	@Override
	public boolean createDesign(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *  addInstanceField(String className, String iFieldName, String... modifiers)
	 *  calls addInstanceField in DesignDoc
	 *  
	 *  @param className a String for the class name instance field is a part of
	 *  @param iFieldName a String for the instance field's name
	 *  @param modifiers Strings to tell what modifiers the Instance Field should have
	 *  @return a boolean returning the success (true) or failure of the instance field addition
	 * @throws HBClassNotFoundException 
	 * @throws HBObjectNotFoundException 
	 */
	@Override
	public boolean addInstanceField(String className, String instanceFieldName,
			String... modifiers) throws HBClassNotFoundException, HBObjectNotFoundException {
		return doc.addInstanceField(className, instanceFieldName, modifiers);
	}

	@Override
	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException {
		return doc.addRelationship(fromClass, toClass, relationship);
	}

	@Override
	public boolean addPackage(String packageName, String className) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addInstanceMethod(String className, String methodName,
			String... modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStaticMethod(String className, String methodName,
			String... modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMethod(String className, String methodName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePackage(String className) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRelationship(String fromClass, String toClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeClass(String className) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createClass(String name) throws HBDuplicateObjectFoundException {
		return doc.createClass(name);
	}
	
	@Override
	public boolean createInterface(String name) throws HBDuplicateObjectFoundException {
		return doc.createInterface(name);
	}

}
