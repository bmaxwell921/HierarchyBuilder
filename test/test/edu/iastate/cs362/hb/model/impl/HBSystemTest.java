package test.edu.iastate.cs362.hb.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.controller.ISystemController;
import edu.iastate.cs362.hb.controller.impl.SystemController;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.model.ISystem;
import edu.iastate.cs362.hb.model.impl.HBSystem;

/**
 * Junit tests for the SystemController. Pretty much just do black box testing
 * @author Brandon
 *
 */
public class HBSystemTest {

	private static final String TEST_NAME = "Name";
	
	// Object to test
	private ISystem test;
	
	@Before
	public void setUp() {
		test = new HBSystem();
		test.createDesign(TEST_NAME);
	}
	
	@Test
	public void testCreateDesign() {
		// Using local test here because test has already called the method
		ISystem localTest = new HBSystem();
		Assert.assertTrue("Creating a design should return true.", localTest.createDesign(TEST_NAME));
	}
	
	@Test
	public void testCreateClass() throws HBDuplicateObjectFoundException {
		Assert.assertTrue("Creating a class normally should work", test.createClass(TEST_NAME));
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testCreateClassDup() throws HBDuplicateObjectFoundException {
		test.createClass(TEST_NAME);
		test.createClass(TEST_NAME);
		Assert.fail("Adding two classes with the name name (and default package) should fail");
	}
	
	@Test
	public void testCreateInterface() throws HBDuplicateObjectFoundException {
		Assert.assertTrue("Creating an interface normally should work", test.createInterface(TEST_NAME));
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testCreateInterfaceDup() throws HBDuplicateObjectFoundException {
		test.createInterface(TEST_NAME);
		test.createInterface(TEST_NAME);
		Assert.fail("Adding two classes with the name name (and default package) should fail");
	}
	
	@After
	public void tearDown() {
		test = null;
	}
}
