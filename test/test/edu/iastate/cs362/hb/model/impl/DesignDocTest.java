package test.edu.iastate.cs362.hb.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.exceptions.HBClassNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.impl.HBDesignDoc;

/**
 * Junit tests for the DesignDocTest. Pretty much just do black box testing
 * 
 * @author Brandon
 * 
 */
public class DesignDocTest {
	private static final String TEST_NAME = "Name";

	private static final String OBJECT_NAME = "class";

	// Object to test
	private IDesignDoc test;

	@Before
	public void setUp() {
		test = new HBDesignDoc(TEST_NAME);
	}

	@Test
	public void testCreateDesign() {
		// Should just execute without fail
		IDesignDoc doc = new HBDesignDoc(TEST_NAME);
		Assert.assertEquals(
				"Newly created design doc should have the correct name",
				TEST_NAME, doc.getName());
	}

	@Test
	public void testCreateClass() throws Exception {
		Assert.assertTrue("Creating a class normally should work",
				test.createClass(OBJECT_NAME));
	}

	@Test(expected = HBDuplicateObjectFoundException.class)
	public void testCreateClassDup() throws Exception {
		test.createClass(OBJECT_NAME);
		test.createClass(OBJECT_NAME);
		Assert.fail("Adding two classes with the name name (and default package) should fail");
	}

	@Test
	public void testCreateInterface() throws Exception {
		Assert.assertTrue("Creating an interface normally should work",
				test.createInterface(OBJECT_NAME));
	}

	@Test(expected = HBDuplicateObjectFoundException.class)
	public void testCreateInterfaceDup() throws Exception {
		test.createInterface(OBJECT_NAME);
		test.createInterface(OBJECT_NAME);
		Assert.fail("Adding two classes with the name name (and default package) should fail");
	}

	@Test
	public void testAddPackage() throws Exception,
			HBObjectNotFoundException {
		String pkgName = "package";
		test.createClass(OBJECT_NAME);
		Assert.assertTrue("Adding a package to an object should work",
				test.addPackage(pkgName, OBJECT_NAME));
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddPackageNotFound() throws Exception {
		String pkgName = "package";
		test.addPackage(pkgName, OBJECT_NAME);
	}
	
	@Test
	public void testAddInstanceField() throws Exception {
		final String instanceFieldName = "aField";
		final String modifier = "itsaModifier";
		test.createClass(OBJECT_NAME);
		Assert.assertTrue("Adding an instance field, MSS.", 
				test.addInstanceField(OBJECT_NAME, instanceFieldName, modifier));
	}
	
	@Test
	public void testAddInstanceField2ElectricBoogaloo() throws Exception {
		final String instanceFieldName = "iMadeThisTestForFun";
		final String modifier = "awesome";
		final String className = "bestTestEver";
		test.createClass(className);
		Assert.assertTrue("Adding an instance field, MSS.", 
				test.addInstanceField(className, instanceFieldName, modifier));
	}
	
	@Test(expected = HBObjectNotFoundException.class)
	public void testAddInstanceFieldToNonexistentClass() throws Exception {
		final String instanceFieldName = "themagic";
		final String modifier = "oftelevision";
		test.createClass(OBJECT_NAME);
		test.addInstanceField("APPLESAUCE!", instanceFieldName, modifier);
	}
	
	@Test(expected = HBClassNotFoundException.class)
	public void testAddInstaceFieldToInterface() throws Exception {
		final String instanceFieldName = "AREYOUSTILLTHERE?";
		final String modifier = "turretVoice";
		test.createInterface(OBJECT_NAME);
		test.addInstanceField(OBJECT_NAME, instanceFieldName, modifier);
	}
	
	@Test
	public void addRelationshipTest() throws Exception{
		final String fromClass = "thistestName";
		final String toClass = "soundsLikeIm";
		final String relationshipName = "aPsychologist";
		test.createClass(fromClass);
		test.createClass(toClass);
		Assert.assertTrue("Adding a relationship to existing Objects should work", 
				test.addRelationship(fromClass, toClass, relationshipName));
	}
	
	@Test
	public void addRelationshipTest2() throws Exception{
		final String fromInterface = "genericName";
		final String toClass = "unassumingName";
		final String relationshipName = "theyfittogether";
		test.createInterface(fromInterface);
		test.createClass(toClass);
		Assert.assertTrue("Adding a relationship to existing Objects should work", 
				test.addRelationship(fromInterface, toClass, relationshipName));
	}
	
	@Test(expected = HBObjectNotFoundException.class)
	public void addRelationshipTestNonExistentClass() throws Exception{
		final String fromClass = "thistestName";
		final String toClass = "soundsLikeIm";
		final String relationshipName = "aPsychologist";
		test.createClass(fromClass);
		Assert.assertTrue("Adding a relationship to existing Objects should work", 
				test.addRelationship(fromClass, toClass, relationshipName));
	}

	@Test
	public void testAddInstanceMethodToClass() throws Exception {
		final String METHOD_NAME = "method";
		final String params = "String:name,int:id";
		final String modifier = "i";
		test.createClass(OBJECT_NAME);
		Assert.assertTrue("Adding an instance method normally should work",
				test.addInstanceMethod(OBJECT_NAME, METHOD_NAME, params, modifier));
	}
	
	@Test
	public void testAddInstanceMethodToInterface() throws Exception {
		final String METHOD_NAME = "method";
		final String params = "String:name,int:id";
		final String modifier = "i";
		test.createInterface(OBJECT_NAME);
		Assert.assertTrue("Adding an instance method normally should work",
				test.addInstanceMethod(OBJECT_NAME, METHOD_NAME, params, modifier));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddInstanceMethodWithoutClass() throws Exception {
		final String METHOD_NAME = "method";
		final String params = "String:name,int:id";
		final String modifier = "i";
		test.addInstanceMethod(OBJECT_NAME, METHOD_NAME, params, modifier);
	}
	
	//@Test (expected = MalformattedCommandException.class)
	public void testAddInstanceMethodInvalidParamsMissing() throws Exception {
		// TODO I think this should be handled in the CommandParser
		final String METHOD_NAME = "method";
		final String params = "String:name,int:";
		final String modifier = "i";
		test.createInterface(OBJECT_NAME);
		test.addInstanceMethod(OBJECT_NAME, METHOD_NAME, params, modifier);
	}
	
	@Test
	public void testAddStaticMethodToClass() throws Exception {
		final String METHOD_NAME = "method";
		final String params = "String:name,int:id";
		final String modifier = "i";
		test.createClass(OBJECT_NAME);
		Assert.assertTrue("Adding an instance method normally should work",
				test.addStaticMethod(OBJECT_NAME, METHOD_NAME, params, modifier));
	}
	
	@Test
	public void testAddStaticMethodToInterface() throws Exception {
		final String METHOD_NAME = "method";
		final String params = "String:name,int:id";
		final String modifier = "i";
		test.createInterface(OBJECT_NAME);
		Assert.assertTrue("Adding an instance method normally should work",
				test.addStaticMethod(OBJECT_NAME, METHOD_NAME, params, modifier));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddStaticMethodWithoutClass() throws Exception {
		final String METHOD_NAME = "method";
		final String params = "String:name,int:id";
		final String modifier = "i";
		test.addStaticMethod(OBJECT_NAME, METHOD_NAME, params, modifier);
	}
	
	//@Test (expected = MalformattedCommandException.class)
	public void testAddStaticMethodInvalidParamsMissing() throws Exception {
		// TODO I think this should be handled in the CommandParser
		final String METHOD_NAME = "method";
		final String params = "String:name,int:";
		final String modifier = "i";
		test.createInterface(OBJECT_NAME);
		test.addStaticMethod(OBJECT_NAME, METHOD_NAME, params, modifier);
	}
	
	@Test
	public void testRemoveRelationship() throws Exception{
		final String fromClass = "genericName";
		final String toClass = "unassumingName";
		final String relationshipName = "theyfittogether";
		test.createClass(fromClass);
		test.createClass(toClass);
		test.addRelationship(fromClass, toClass, relationshipName);
		Assert.assertTrue("Test normal Remove", test.removeRelationship(fromClass, toClass));
	}
	
	@Test(expected = HBRelationshipNotFoundException.class)
	public void testRemoveRelationshipThatDoesntExist() throws Exception{
		final String fromClass = "imaClass";
		final String toClass = "imaClass2";
		final String relationshipName = "theyfittogether";
		test.createClass(fromClass);
		test.createClass(toClass);
		test.addRelationship(fromClass, toClass, relationshipName);
		test.removeRelationship(fromClass, toClass);
		test.removeRelationship(fromClass, toClass);
	}
	
	@Test(expected = HBRelationshipNotFoundException.class)
	public void testRemoveRelationshipThatDoesntExist1() throws Exception{
		String fromClass = "imaClass";
		String toClass = "imaClass2";
		test.createClass(fromClass);
		test.createClass(toClass);
		test.removeRelationship(fromClass, toClass);
	}
	
	@Test(expected = HBObjectNotFoundException.class) 
	public void testRemoveRelationshipClassesNotReal() throws Exception{
		test.removeRelationship("IMNOTREAL", "NEITHERAMI");
	}
	
	@Test(expected = HBObjectNotFoundException.class) 
	public void testRemoveRelationshipClassesNotReal1() throws Exception{
		test.createClass("IMNOTREAL");
		test.removeRelationship("IMNOTREAL", "NEITHERAMI");
	}
	
	@Test(expected = HBObjectNotFoundException.class) 
	public void testRemoveRelationshipClassesNotReal2() throws Exception{
		test.createClass("BUTIAM");
		test.removeRelationship("IMNOTREAL", "BUTIAM");
	}
	
	@Test
	public void testRemoveClass() throws Exception {
		final String name = "CLASS";
		test.createClass(name);
		Assert.assertTrue("Test Normal Remove Class.", test.removeObj(name));
	}
	
	@Test(expected = HBObjectNotFoundException.class)
	public void testRemoveClassNotFound() throws Exception {
		test.removeObj("NotReal");
	}

	@Test
	public void testThisStuff() throws Exception{
		test.createClass(TEST_NAME);
		test.createClass("CAATS");
		test.addRelationship(TEST_NAME, "CAATS", "extends");
		//TODO can we get actual tests for this?
//		System.out.println(test.list());
		//String.replace("\\s+", "");
		Assert.assertEquals(true, true);
	}
	
	@After
	public void tearDown() {
		test = null;
	}
}
