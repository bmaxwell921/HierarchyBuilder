package edu.iastate.cs362.hb.model.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBMethodNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.exceptions.MalformattedInputException;
import edu.iastate.cs362.hb.export.IExporter;
import edu.iastate.cs362.hb.export.impl.JsonExporter;
import edu.iastate.cs362.hb.export.impl.SourceExporter;
import edu.iastate.cs362.hb.export.impl.XmlExporter;
import edu.iastate.cs362.hb.importer.IImporter;
import edu.iastate.cs362.hb.importer.impl.JsonImporter;
import edu.iastate.cs362.hb.importer.impl.SourceImporter;
import edu.iastate.cs362.hb.importer.impl.XmlImporter;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.ISystem;

public class HBSystem implements ISystem {

	private IDesignDoc doc;
	private IExporter exporter;
	private IImporter importer;

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
	 * @param className
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
	public boolean addInstanceField(String className, String fieldName,
			String fieldType, String... modifiers) throws Exception {
		return doc.addInstanceField(className, fieldName, fieldType, modifiers);
	}

	@Override
	public boolean addRelationship(String superType, String subType, String rel)
			throws Exception {
		return doc.addRelationship(superType, subType, rel);
	}

	@Override
	public boolean addPackage(String pkgName, String objName) throws Exception {
		return doc.addPackage(pkgName, objName);
	}

	@Override
	public boolean addInstanceMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception {
		return doc.addInstanceMethod(objName, methodName, params, modifiers);
	}

	@Override
	public boolean addStaticMethod(String objName, String methodName,
			String params, String... modifiers) throws Exception {
		return doc.addStaticMethod(objName, methodName, params, modifiers);
	}

	/**
	 * @throws HBMultipleObjectsFoundException
	 * @throws HBObjectNotFoundException
	 * @throws HBMethodNotFoundException
	 * 
	 */
	@Override
	public boolean removeMethod(String objName, String methodName)
			throws Exception {
		return doc.removeMethod(objName, methodName);
	}

	@Override
	public boolean removePackage(String objName) throws Exception {
		return doc.removePackage(objName);
	}

	@Override
	public boolean removeRelationship(String superType, String subType)
			throws Exception {
		return doc.removeRelationship(superType, subType);
	}

	@Override
	public boolean removeObj(String objName) throws Exception {
		return doc.removeObj(objName);
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
	public List<IObjectBox> getMatchingObjects(String name) {
		return doc.getMatchingObjects(name);
	}

	@Override
	public String listDesign() {
		if (doc == null)
			return "";
		return doc.list();
	}

	@Override
	public String listObject(String name) throws Exception {
		return doc.listObject(name);
	}

	@Override
	public boolean changeName(String name, String newName) throws Exception {
		return doc.changeName(name, newName);
	}

	@Override
	public boolean changePackage(String name, String packageName)
			throws Exception {
		return doc.changePackage(name, packageName);
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
	public boolean changeModifiers(long objId, Set<String> modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeclassField(long objId, long fieldId, String type,
			Set<String> modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeClassMethod(long objId, long methodId,
			String methodName, List<String> methodArgs, Set<String> modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String listCachedMethod(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listCachedVariable(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long cacheMethod(String methodName, String returnType,
			Set<String> modifiers, List<String> arguments) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long cacheVariable(String name, String type, Set<String> modifiers) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String listCachedModifierSet(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long cacheModifierSet(Set<String> modifiers) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return null;
	}

}
