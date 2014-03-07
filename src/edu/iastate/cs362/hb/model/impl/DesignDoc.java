package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IInstanceField;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.tree.IHBTree;
import edu.iastate.cs362.hb.model.tree.impl.HBTree;

public class DesignDoc implements IDesignDoc{
	
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
			String relationship) throws HBObjectNotFoundException {
		IObject from = tree.getObject(fromClass);
		IObject to = tree.getObject(toClass);
		//TODO fix this.
		Relationship r = createRelationship(relationship);
		return tree.addRelationship(from, to, r);
	}
	
	

	private Relationship createRelationship(String relationship) {
		return new Relationship(relationship);
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
	public boolean createClass(String name) throws HBDuplicateObjectFoundException {
		IClass clazz = new HBClass(name);
		return tree.addObject(clazz);
	}
	
	@Override
	public boolean createInterface(String name) throws HBDuplicateObjectFoundException {
//		IInterface interf = new HBInterface(name);
//		return tree.addInterface(interf);
		return false;
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
