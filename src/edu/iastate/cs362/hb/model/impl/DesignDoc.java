package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IInstanceField;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.tree.IHBTree;
import edu.iastate.cs362.hb.model.tree.impl.HBTree;

public class DesignDoc implements IDesignDoc {

	// The name of the design doc. Name of the file to save
	private String name;

	// Tree representing the class hierarchy
	private IHBTree tree;

	public DesignDoc(String name) {
		this.name = name;
		this.tree = new HBTree();
	}

	@Override
	public boolean addInstanceField(String className, String instanceFieldName,
			String... modifiers) throws HBClassNotFoundException,
			HBObjectNotFoundException {
		IObject obj = tree.getObject(className);
		// if obj is not a Class (its an interface) then we can't add an
		// instance field
		if (!(obj instanceof HBClass))
			throw new HBClassNotFoundException(
					"Name entered was not a name of a Class.");
		// now we know obj is an HBClass.
		HBClass cl = (HBClass) obj;

		IInstanceField i = new InstanceField(instanceFieldName);
		i.addModifiers(modifiers);

		return cl.addInstanceField(i);
	}

	@Override
	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException {
		IObject from = tree.getObject(fromClass);
		IObject to = tree.getObject(toClass);
		Relationship r = new Relationship(relationship);
		return tree.addRelationship(from, to, r);
	}

	@Override
	public boolean addPackage(String packageName, String className)
			throws HBObjectNotFoundException {
		IObject clazz = tree.getObject(className);
		clazz.addPackage(packageName);
		return true;
	}

	@Override
	public boolean addInstanceMethod(String className, String methodName,
			String params, String... modifiers)
			throws HBObjectNotFoundException, MalformattedCommandException,
			HBDuplicateMethodException {
		return addMethod(className, methodName, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException {
		return addMethod(className, methodName, params, modifiers);
	}

	private boolean addMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException {
		IMethod method = new HBMethod(methodName);
		method.addModifiers(modifiers);
		method.addArguments(params);

		IObject clazz = tree.getObject(className);
		return clazz.addMethod(method);
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
	public boolean createClass(String name)
			throws HBDuplicateObjectFoundException {
		IClass clazz = new HBClass(name);
		return tree.addObject(clazz);
	}

	@Override
	public boolean createInterface(String name)
			throws HBDuplicateObjectFoundException {
		IObject interf = new HBInterface(name);
		return tree.addObject(interf);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name != null && this.name.equals(name);
	}
}
