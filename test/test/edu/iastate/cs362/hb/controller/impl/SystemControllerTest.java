package test.edu.iastate.cs362.hb.controller.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.controller.ISystemController;
import edu.iastate.cs362.hb.controller.impl.SystemController;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.model.impl.HBSystem;

/**
 * Junit tests for the SystemController. Pretty much just do black box testing
 * @author Brandon
 *
 */
public class SystemControllerTest {

	private static final String TEST_NAME = "Name";
	
	// Object to test
	private ISystemController test;
	
	@Before
	public void setUp() {
		test = new SystemController(new HBSystem());
		test.createDesign(TEST_NAME);
	}
	
	@Test
	public void testCreateDesign() {
		// Using local test here because test has already called the method
		ISystemController localTest = new SystemController(new HBSystem());
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
	
	@After
	public void tearDown() {
		test = null;
	}
}
