package edu.iastate.cs362.hb.controller.impl;

import edu.iastate.cs362.hb.controller.ISystemController;

public class SystemController implements ISystemController{

	@Override
	public boolean createDesign(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addInstanceField(String className, String iFieldName,
			String... modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRelationship(String fromClass, String toClass,
			String relationship) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean createClass(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
