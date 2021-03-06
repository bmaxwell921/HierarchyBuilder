package edu.iastate.cs362.hb.model.impl;

import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.main.IdManager;
import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.IVariable;
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
	public boolean addInstanceField(long objId, String fieldName, String fieldType,
			String... modifiers) throws Exception {
		IObject obj = tree.getObject(objId);
		// if obj is not a Class (its an interface) then we can't add an
		// instance field
		if (!(obj instanceof HBClass))
			throw new HBClassNotFoundException(
					"Name entered was not a name of a Class.");
		// now we know obj is an HBClass.
		HBClass cl = (HBClass) obj;

		IVariable i = new HBVariable(fieldName, fieldType);
		i.addModifiers(modifiers);

		return cl.addInstanceField(i);
	}

	@Override
	public boolean addRelationship(long fromName, long toName, String rel)
			throws Exception {
		IObject from = tree.getObject(fromName);
		IObject to = tree.getObject(toName);
		HBRelationship r = new HBRelationship(rel);
		return tree.addRelationship(from, to, r);
	}

	@Override
	public boolean addPackage(long objId, String pkg) throws Exception {
		IObject clazz = tree.getObject(objId);
		// IdManager part of UI, will not show up in design
		IdManager.getInstance().updateInfo(clazz.getId(), pkg, clazz.getName());
		return clazz.addPackage(pkg);
	}

	@Override
	public boolean addInstanceMethod(long objId, String methodName, String returnType,
			String params, String... modifiers) throws Exception {
		return addMethod(objId, methodName, returnType, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(long objId, String methodName, String returnType,
			String params, String... modifiers) throws Exception {
		return addMethod(objId, methodName, returnType, params, modifiers);
	}

	private boolean addMethod(long objId, String methodName, String returnType, String params,
			String... modifiers) throws Exception {
		IMethod method = new HBMethod(methodName);
		method.addModifiers(modifiers);
		method.addArguments(params);
		method.addReturnType(returnType);
		IObject clazz = tree.getObject(objId);

		return clazz.addMethod(method);
	}

	@Override
	public boolean removeMethod(long objId, String methodName)
			throws Exception {
		HBClass clazz = (HBClass) tree.getObject(objId);
		IMethod method = clazz.getMethod(methodName);
		clazz.removeMethod(method);
		return true;
	}

	@Override
	public boolean removePackage(long objId) throws Exception {
		IObject toRem = tree.getObject(objId);
		// IdManager part of UI, will not show up in design
		IdManager.getInstance().updateInfo(toRem.getId(), toRem.getPackage(), toRem.getName());
		return toRem.removePackage();
	}

	@Override
	public boolean removeRelationship(long superId, long subId)
			throws Exception {
		IObject from = tree.getObject(superId);
		IObject to = tree.getObject(subId);
		return tree.removeRelationship(from, to);
	}

	@Override
	public boolean removeObj(long remId) throws Exception,
			HBMultipleObjectsFoundException {
		IObject toRem = tree.getObject(remId);
		// IdManager part of UI, will not show up in design
		IdManager.getInstance().remove(toRem.getId());
		return tree.removeObject(toRem);
	}

	@Override
	public boolean createClass(String name) throws Exception {
		IClass clazz = new HBClass(name);
		clazz.addModifiers("public");
		// IdManager part of UI, will not show up in design
		IdManager.getInstance().registerObject(clazz, clazz.getPackage(), clazz.getName());
		return tree.addObject(clazz);
	}

	@Override
	public boolean createInterface(String name) throws Exception {
		IObject interf = new HBInterface(name);
		interf.addModifiers("public");
		// IdManager part of UI, will not show up in design
		IdManager.getInstance().registerObject(interf, interf.getPackage(), interf.getName());
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
	public String list() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Design: ");
		sb.append(this.name);
		sb.append("\n===================================\n");
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
	public String listObject(long objId) throws Exception {
		return tree.getObject(objId).list();
	}

	@Override
	public boolean changeName(long objId, String newName) throws Exception {
		IObject obj = tree.getObject(objId);
		// IdManager part of UI, will not show up in design
		IdManager.getInstance().updateInfo(obj.getId(), obj.getPackage(), newName);
		return obj.changeName(newName);
	}

	@Override
	public boolean changePackage(long objId, String pkgName)
			throws Exception {
		IObject obj = tree.getObject(objId);
		// IdManager part of UI, will not show up in design
		IdManager.getInstance().updateInfo(obj.getId(), pkgName, obj.getName());
		return obj.changePackage(pkgName);
	}

	@Override
	public void traverse(IHBTreeVisitor visitor) {
		tree.traverse(visitor);
	}

	@Override
	public void addAllInner(Set<? extends IObject> interfaces,
			Set<? extends IObject> classes) {
		try {
			long biggestId = Integer.MIN_VALUE;
			// Add the objects
			if (interfaces != null) {
				for (IObject interf : interfaces) {
					tree.addObject(interf);
					// IdManager part of UI, will not show up in design
					IdManager.getInstance().loadInfo(interf.getId(), interf.getPackage(), interf.getName());
					if (interf.getId() > biggestId) {
						biggestId = interf.getId();
					}
				}
			}
			if (classes != null) {
				for (IObject clazz : classes) {
					tree.addObject(clazz);
					// IdManager part of UI, will not show up in design
					IdManager.getInstance().loadInfo(clazz.getId(), clazz.getPackage(), clazz.getName());
					if (clazz.getId() > biggestId) {
						biggestId = clazz.getId();
					}
				}
			}
			// IdManager part of UI, will not show up in design
			IdManager.getInstance().setNextId(biggestId + 1);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean changeModifiers(long objId, Set<String> modifiers) throws Exception {
		IObject toUse = tree.getObject(objId);
		return toUse.changeModifiers(modifiers);
	}

	@Override
	public boolean changeClassField(long objId, long fieldId, String fieldName, String type,
			Set<String> modifiers) throws Exception {
		IObject toUse = tree.getObject(objId);
		if(!(toUse instanceof HBClass)){
			return false;
		}
		return toUse.changeClassField(fieldId, fieldName, type, modifiers);
	}

	@Override
	public boolean changeClassMethod(long objId, long methodId,
			String methodName, String returnType, List<String> methodArgs, Set<String> modifiers) throws Exception {
		IObject toUse = tree.getObject(objId);
		return toUse.changeClassMethod(methodId, methodName, returnType, methodArgs, modifiers);
	}

	@Override
	public boolean removeField(long id, String name) throws Exception {
		IObject toUse = tree.getObject(id);
		if(!(toUse instanceof HBClass)){
			return false;
		}
		return ((HBClass) toUse).removeField(name);
	}
}
