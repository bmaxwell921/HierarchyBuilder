package edu.iastate.cs362.hb.controller.impl;

import edu.iastate.cs362.hb.controller.ISystemController;
import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateClassFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.ISystem;

public class SystemController implements ISystemController{

	private ISystem system;
	
	/**
	 * Constructus the SystemController for the given System
	 * @param system
	 */
	public SystemController(ISystem system) {
		this.system = system;
	}
	
	@Override
	public boolean createDesign(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 *  addInstanceField(String className, String iFieldName, String... modifiers)
	 *  calls addInstanceField in System
	 *  
	 *  @param className a String for the class name instance field is a part of
	 *  @param iFieldName a String for the instance field's name
	 *  @param modifiers Strings to tell what modifiers the Instance Field should have
	 *  @return a boolean returning the success (true) or failure of the instance field addition
	 * @throws HBClassNotFoundException 
	 * @throws HBObjectNotFoundException 
	 */
	@Override
	public boolean addInstanceField(String className, String iFieldName,
			String... modifiers) throws HBClassNotFoundException, HBObjectNotFoundException {
		return system.addInstanceField(className, iFieldName, modifiers);
	}

	@Override
	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException{
		return system.addRelationship(fromClass, toClass, relationship);
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
	public boolean removeRelationship(String fromString, String toString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeClass(String toRemove) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createClass(String name) throws HBDuplicateClassFoundException {
		return system.createClass(name);
	}
	
}
