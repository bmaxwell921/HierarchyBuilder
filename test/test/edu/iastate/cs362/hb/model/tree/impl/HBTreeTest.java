package test.edu.iastate.cs362.hb.model.tree.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.tree.impl.HBTree;

/**
 * Tests for the HBTree class
 * @author Brandon
 *
 */
public class HBTreeTest {

	// THE TREE!
	private HBTree test;
	
	@Before
	public void setUp() {
		test = new HBTree();
	}
	
	@Test
	public void testAddClass() throws HBObjectNotFoundException, HBDuplicateObjectFoundException {
		IObject clazz = new HBClass("Test");
		test.addObject(clazz);
		Assert.assertEquals(clazz, test.getObject("Test"));
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testDuplicateClass() throws HBDuplicateObjectFoundException {
		IObject clazz = new HBClass("Test");
		test.addObject(clazz);
		test.addObject(clazz);
	}
	
	@After
	public void tearDown() {
		test = null;
	}

}
