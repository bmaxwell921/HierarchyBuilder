package edu.iastate.cs362.hb.model.impl;

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
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IInstanceField;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.ITree;
import edu.iastate.cs362.hb.model.tree.Pair;
import edu.iastate.cs362.hb.model.tree.impl.HBTree;

public class HBDesignDoc implements IDesignDoc {

	// The name of the design doc. Name of the file to save
	private String name;

	// Tree representing the class hierarchy
	private ITree tree;

	public HBDesignDoc(String name) {
		this.name = name;
		this.tree = new HBTree();
	}

	@Override
	public boolean addInstanceField(String className, String instanceFieldName,
			String... modifiers) throws HBClassNotFoundException,
			HBObjectNotFoundException, HBMultipleObjectsFoundException {
		IObject obj = tree.getObject(className);
		// if obj is not a Class (its an interface) then we can't add an
		// instance field
		if (!(obj instanceof HBClass))
			throw new HBClassNotFoundException(
					"Name entered was not a name of a Class.");
		// now we know obj is an HBClass.
		HBClass cl = (HBClass) obj;

		IInstanceField i = new HBInstanceField(instanceFieldName);
		i.addModifiers(modifiers);

		return cl.addInstanceField(i);
	}

	@Override
	public boolean addRelationship(String fromClass, String toClass,
			String relationship) throws HBObjectNotFoundException,
			HBDuplicateRelationshipException, HBMultipleObjectsFoundException {
		IObject from = tree.getObject(fromClass);
		IObject to = tree.getObject(toClass);
		HBRelationship r = new HBRelationship(relationship);
		return tree.addRelationship(from, to, r);
	}

	@Override
	public boolean addPackage(String packageName, String className)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		IObject clazz = tree.getObject(className);
		clazz.addPackage(packageName);
		return true;
	}

	@Override
	public boolean addInstanceMethod(String className, String methodName,
			String params, String... modifiers)
			throws HBObjectNotFoundException, MalformattedCommandException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException {
		return addMethod(className, methodName, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException {
		return addMethod(className, methodName, params, modifiers);
	}

	private boolean addMethod(String className, String methodName,
			String params, String... modifiers)
			throws MalformattedCommandException, HBObjectNotFoundException,
			HBDuplicateMethodException, HBMultipleObjectsFoundException {
		IMethod method = new HBMethod(methodName);
		method.addModifiers(modifiers);
		method.addArguments(params);

		IObject clazz = tree.getObject(className);
		return clazz.addMethod(method);
	}

	@Override
	public boolean removeMethod(String className, String methodName) throws HBObjectNotFoundException, HBMultipleObjectsFoundException, HBMethodNotFoundException {
		HBClass clazz = (HBClass) tree.getObject(className);
		IMethod method = clazz.getMethod(methodName);
		clazz.removeMethod(method);
		return true;
	}

	@Override
	public boolean removePackage(String className) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		IObject toRem = tree.getObject(className);
		return toRem.removePackage();
	}

	@Override
	public boolean removeRelationship(String fromClass, String toClass) throws 
			HBMultipleObjectsFoundException, HBObjectNotFoundException, HBRelationshipNotFoundException {
		IObject from = tree.getObject(fromClass);
		IObject to = tree.getObject(toClass);
		return tree.removeRelationship(from, to);
	}

	@Override
	public boolean removeClass(String toRemove) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		IObject toRem = tree.getObject(toRemove);
		return tree.removeObject(toRem);
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

	@Override
	public List<IObjectBox> getMatchingObjects(String name) {
		return tree.getMatchingObjects(name);
	}

	@Override
	public String list() {
		final StringBuilder sb = new StringBuilder();
		tree.traverse(new IHBTreeVisitor(){
			@Override
			public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes) {
				sb.append(o.list());
				sb.append("\n");
			}
		});
		return sb.toString();
	}

	@Override
	public String listObject(String name) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return tree.getObject(name).list();
	}

	@Override
	public boolean changeName(String name, String newName) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return tree.getObject(name).changeName(newName);
	}

	@Override
	public boolean changePackage(String name, String packageName) throws HBObjectNotFoundException, HBMultipleObjectsFoundException {
		return tree.getObject(name).changePackage(packageName);
	}

	@Override
	public void traverse(IHBTreeVisitor visitor) {
		tree.traverse(visitor);
		
	}
}
