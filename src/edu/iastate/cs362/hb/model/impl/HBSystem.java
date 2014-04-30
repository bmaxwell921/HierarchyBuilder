package edu.iastate.cs362.hb.model.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBMethodNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedInputException;
import edu.iastate.cs362.hb.export.IExporter;
import edu.iastate.cs362.hb.export.impl.JsonExporter;
import edu.iastate.cs362.hb.export.impl.SourceExporter;
import edu.iastate.cs362.hb.export.impl.XmlExporter;
import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.importer.impl.JsonImporter;
import edu.iastate.cs362.hb.importer.impl.SourceImporter;
import edu.iastate.cs362.hb.importer.impl.XmlImporter;
import edu.iastate.cs362.hb.main.CacheManager;
import edu.iastate.cs362.hb.main.IdManager;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.ISystem;
import edu.iastate.cs362.hb.model.IVariable;
import edu.iastate.cs362.hb.model.attributes.Identifiable;

public class HBSystem implements ISystem {

	private IDesignDoc doc;
	private IExporter exporter;
	private IImporter importer;
	private CacheManager cache;

	public HBSystem() {
		cache = CacheManager.getInstance();
	}

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
	 * @param objId
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
	public boolean addInstanceField(long objId, String fieldName,
			String fieldType, String... modifiers) throws Exception {
		return doc.addInstanceField(objId, fieldName, fieldType, modifiers);
	}

	@Override
	public boolean addRelationship(long superId, long subId, String rel)
			throws Exception {
		return doc.addRelationship(superId, subId, rel);
	}

	@Override
	public boolean addPackage(long objId, String pkgName) throws Exception {
		return doc.addPackage(objId, pkgName);
	}

	@Override
	public boolean addInstanceMethod(long objId, String methodName,
			String params, String... modifiers) throws Exception {
		return doc.addInstanceMethod(objId, methodName, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(long objId, String methodName,
			String params, String... modifiers) throws Exception {
		return doc.addStaticMethod(objId, methodName, params, modifiers);
	}

	/**
	 * @throws HBMultipleObjectsFoundException
	 * @throws HBObjectNotFoundException
	 * @throws HBMethodNotFoundException
	 * 
	 */
	@Override
	public boolean removeMethod(long objId, String methodName)
			throws Exception {
		return doc.removeMethod(objId, methodName);
	}

	@Override
	public boolean removePackage(long objId) throws Exception {
		return doc.removePackage(objId);
	}

	@Override
	public boolean removeRelationship(long superId, long subId)
			throws Exception {
		return doc.removeRelationship(superId, subId);
	}

	@Override
	public boolean removeObj(long remId) throws Exception {
		return doc.removeObj(remId);
	}

	@Override
	public boolean createClass(String name) throws Exception {
		return doc.createClass(name);
	}

	@Override
	public boolean createInterface(String name) throws Exception {
		return doc.createInterface(name);
	}

	@Override
	public String listDesign() {
		if (doc == null)
			return "";
		return doc.list();
	}

	@Override
	public String listObject(long objId) throws Exception {
		return doc.listObject(objId);
	}

	@Override
	public boolean changeName(long objId, String newName) throws Exception {
		return doc.changeName(objId, newName);
	}

	@Override
	public boolean changePackage(long objId, String pkgName)
			throws Exception {
		return doc.changePackage(objId, pkgName);
	}

	@Override
	public boolean exportDesignXML(String path) {
		exporter = new XmlExporter();
		return exporter.doExport(path, doc);
	}

	@Override
	public boolean exportDesignJSON(String path) {
		exporter = new JsonExporter();
		return exporter.doExport(path, doc);
	}

	@Override
	public boolean exportDesignSource(String path) {
		exporter = new SourceExporter();
		return exporter.doExport(path, doc);
	}

	@Override
	public boolean importDesignXML(String path) throws FileNotFoundException,
			IOException, MalformattedInputException {
		importer = new XmlImporter();
		doc = importer.doImport(path);
		return true;
	}

	@Override
	public boolean importDesignJSON(String path) throws FileNotFoundException,
			IOException, MalformattedInputException {
		importer = new JsonImporter();
		doc = importer.doImport(path);
		return true;
	}

	@Override
	public boolean importDesignSource(String path)
			throws FileNotFoundException, IOException,
			MalformattedInputException {
		importer = new SourceImporter();
		doc = importer.doImport(path);
		return true;
	}

	@Override
	public boolean changeModifiers(long objId, Set<String> modifiers)
			throws Exception {
		return doc.changeModifiers(objId, modifiers);
	}

	@Override
	public boolean changeClassField(long objId, long fieldId, String fieldName,
			String type, Set<String> modifiers) throws Exception {
		return doc.changeClassField(objId, fieldId, fieldName, type, modifiers);
	}

	@Override
	public boolean changeClassMethod(long objId, long methodId,
			String methodName, List<String> methodArgs, Set<String> modifiers)
			throws Exception {
		return doc.changeClassMethod(objId, methodId, methodName, methodArgs,
				modifiers);
	}

	@Override
	public String listCachedMethod(long id) {
		return CacheManager.getMappings(id);
	}

	@Override
	public String listCachedVariable(long id) {
		return CacheManager.getMappings(id);
	}

	@Override
	public long cacheMethod(String methodName, String returnType,
			Set<String> modifiers, List<String> arguments) {
		IMethod method = new HBMethod(methodName);
		// any of these methods may be null check for that.
		if (arguments != null) {
			method.addArguments(arguments);
		}
		if (modifiers != null) {
			method.addModifiers(modifiers);
		}
		if (returnType != null) {
			method.addReturnType(returnType);
		}
		return cache.addItem(method, methodName, "method");
	}

	@Override
	public long cacheVariable(String name, String type, Set<String> modifiers) {
		IVariable var = new HBVariable(name, type);
		var.addModifiers(modifiers);
		return cache.addItem(var, name, "variable");
	}

	@Override
	public String listCachedModifierSet(long id) {
		return CacheManager.getMappings(id);
	}

	@Override
	public long cacheModifierSet(Set<String> modifiers) {
		return cache.addItem(modifiers, "Modifier Set", "modifier");
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addObject(String type, Object obj) throws Exception {
		switch (type.toUpperCase()) {
		case "METHOD":
			IMethod method = (HBMethod) obj;
			StringBuilder sb = new StringBuilder();
			StringBuilder mod = new StringBuilder();
			for (IVariable s : method.getArguments()) {
				sb.append(s.getType() + ":" + s.getName() + ",");
			}
			for (String s : method.getModifiers()) {
				mod.append(s);
			}
//			return doc.addInstanceMethod(method.getName(), method.getName(),
//					sb.toString(), mod.toString());
			return false;
		case "VARIABLE":
		case "INSTANCE":

			break;
		}
		return false;
	}

}
