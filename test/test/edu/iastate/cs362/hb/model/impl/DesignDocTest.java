package test.edu.iastate.cs362.hb.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.model.IDesignDoc;
import edu.iastate.cs362.hb.model.ISystem;
import edu.iastate.cs362.hb.model.impl.DesignDoc;
import edu.iastate.cs362.hb.model.impl.HBSystem;

/**
 * Junit tests for the DesignDocTest. Pretty much just do black box testing
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
		test = new DesignDoc(TEST_NAME);
	}
	
	@Test
	public void testCreateDesign() {
		// Should just execute without fail
		IDesignDoc doc = new DesignDoc(TEST_NAME);
		Assert.assertEquals("Newly created design doc should have the correct name", TEST_NAME, doc.getName());
	}
	
	@Test
	public void testCreateClass() throws HBDuplicateObjectFoundException {
		Assert.assertTrue("Creating a class normally should work", test.createClass(OBJECT_NAME));
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testCreateClassDup() throws HBDuplicateObjectFoundException {
		test.createClass(OBJECT_NAME);
		test.createClass(OBJECT_NAME);
		Assert.fail("Adding two classes with the name name (and default package) should fail");
	}
	
	@Test
	public void testCreateInterface() throws HBDuplicateObjectFoundException {
		Assert.assertTrue("Creating an interface normally should work", test.createInterface(OBJECT_NAME));
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testCreateInterfaceDup() throws HBDuplicateObjectFoundException {
		test.createInterface(OBJECT_NAME);
		test.createInterface(OBJECT_NAME);
		Assert.fail("Adding two classes with the name name (and default package) should fail");
	}
	
	@After
	public void tearDown() {
		test = null;
	}
}
