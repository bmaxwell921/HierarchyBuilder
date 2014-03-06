package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IInstanceField;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.tree.IHBTree;

public class DesignDoc implements IDesignDoc{
	
	private IHBTree tree;

	@Override
	public boolean addInstanceField(String className, String instanceFieldName,
			String... modifiers) throws HBClassNotFoundException, HBObjectNotFoundException {
		IObject obj = tree.getObject(className);
		//if obj is not a Class (its an interface) then we can't add an instance field
		if(!(obj instanceof HBClass))
			throw new HBClassNotFoundException("Name entered was not a name of a Class.");
		//now we know obj is an HBClass.
		IInstanceField i = createInstanceField(instanceFieldName, modifiers);
		HBClass cl = (HBClass) obj;
		cl.addInstanceField(i);
		return true;
	}

	@Override
	public IInstanceField createInstanceField(String name, String... modifiers) {
		return new InstanceField(name, modifiers);
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
	public boolean removeRelationship(String fromClass, String toClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeClass(String toRemove) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IClass createClass(String name) {
		return new HBClass(name);
	}
}
