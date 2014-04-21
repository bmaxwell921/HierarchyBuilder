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
	public boolean addInstanceField(String className, String fieldName,
			String... modifiers) throws Exception {
		IObject obj = tree.getObject(className);
		// if obj is not a Class (its an interface) then we can't add an
		// instance field
		if (!(obj instanceof HBClass))
			throw new HBClassNotFoundException(
					"Name entered was not a name of a Class.");
		// now we know obj is an HBClass.
		HBClass cl = (HBClass) obj;

		IInstanceField i = new HBInstanceField(fieldName);
		i.addModifiers(modifiers);

		return cl.addInstanceField(i);
	}

	@Override
	public boolean addRelationship(String fromName, String toName, String rel)
			throws Exception {
		IObject from = tree.getObject(fromName);
		IObject to = tree.getObject(toName);
		HBRelationship r = new HBRelationship(rel);
		return tree.addRelationship(from, to, r);
	}

	@Override
	public boolean addPackage(String pkgName, String objName) throws Exception {
		IObject clazz = tree.getObject(objName);
		clazz.addPackage(pkgName);
		return true;
	}

	@Override
	public boolean addInstanceMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception {
		return addMethod(objName, methodName, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception {
		return addMethod(objName, methodName, params, modifiers);
	}

	private boolean addMethod(String objName, String methodName, String params,
			String... modifiers) throws Exception {
		IMethod method = new HBMethod(methodName);
		method.addModifiers(modifiers);
		method.addArguments(params);

		IObject clazz = tree.getObject(objName);
		return clazz.addMethod(method);
	}

	@Override
	public boolean removeMethod(String objName, String methodName)
			throws Exception {
		HBClass clazz = (HBClass) tree.getObject(objName);
		IMethod method = clazz.getMethod(methodName);
		clazz.removeMethod(method);
		return true;
	}

	@Override
	public boolean removePackage(String objName) throws Exception {
		IObject toRem = tree.getObject(objName);
		return toRem.removePackage();
	}

	@Override
	public boolean removeRelationship(String fromName, String toName)
			throws Exception {
		IObject from = tree.getObject(fromName);
		IObject to = tree.getObject(toName);
		return tree.removeRelationship(from, to);
	}

	@Override
	public boolean removeObj(String rem) throws Exception,
			HBMultipleObjectsFoundException {
		IObject toRem = tree.getObject(rem);
		return tree.removeObject(toRem);
	}

	@Override
	public boolean createClass(String name) throws Exception {
		IClass clazz = new HBClass(name);
		return tree.addObject(clazz);
	}

	@Override
	public boolean createInterface(String name) throws Exception {
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
		sb.append("Design: ");
		sb.append(this.name);
		sb.append("\n");
		tree.traverse(new IHBTreeVisitor() {
			@Override
			public void visit(IObject o,
					Set<Pair<IRelationship, IObject>> superTypes) {
				sb.append(o.list());
				sb.append("\n");
			}
		});
		return sb.toString();
	}

	@Override
	public String listObject(String name) throws Exception {
		return tree.getObject(name).list();
	}

	@Override
	public boolean changeName(String name, String newName) throws Exception {
		return tree.getObject(name).changeName(newName);
	}

	@Override
	public boolean changePackage(String name, String pkgName)
			throws Exception {
		return tree.getObject(name).changePackage(pkgName);
	}

	@Override
	public void traverse(IHBTreeVisitor visitor) {
		tree.traverse(visitor);
	}

	@Override
	public void addAllInner(Set<? extends IObject> interfaces,
			Set<? extends IObject> classes) {
		try {
			// Add the objects
			if (interfaces != null) {
				for (IObject interf : interfaces) {
					tree.addObject(interf);
				}
			}
			if (classes != null) {
				for (IObject clazz : classes) {
					tree.addObject(clazz);
				}
			}

			/*
			 * Then add the relationships TODO how to do this?? I think I'll
			 * need to rework the IObject class to have relationships in it,
			 * otherwise Gson won't export that information
			 */

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
