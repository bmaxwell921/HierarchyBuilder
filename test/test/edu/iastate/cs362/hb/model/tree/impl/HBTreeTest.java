package test.edu.iastate.cs362.hb.model.tree.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.model.IObject;
import edu.iastate.cs362.hb.model.IRelationship;
import edu.iastate.cs362.hb.model.impl.HBClass;
import edu.iastate.cs362.hb.model.impl.HBInterface;
import edu.iastate.cs362.hb.model.impl.HBRelationship;
import edu.iastate.cs362.hb.model.tree.impl.HBTree;

/**
 * Tests for the HBTree class
 * @author Brandon
 *
 */
public class HBTreeTest {

	// THE TREE!
	private HBTree test;
	
	// Common vars
	private IObject clazz = new HBClass("TestClass");
	private IObject clazz2 = new HBClass("TestClass2");
	private IObject interf = new HBInterface("TestInterface");
	private IObject interf2 = new HBInterface("TestInterface2");
	
	private IRelationship impl = new HBRelationship("implements");
	private IRelationship ext = new HBRelationship("extends");
	
	@Before
	public void setUp() {
		test = new HBTree();
	}

	/*
	 * ----------------------------------------------------------------------------
	 * Get Tests
	 * ----------------------------------------------------------------------------
	 */
	
	@Test
	public void testGetObject() throws Exception {
		test.addObject(clazz);
		IObject getRes = test.getObject(clazz.getName());
		Assert.assertEquals("Getting an object that was just added should work", clazz, getRes);
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testGetObjectNotFound() throws Exception {
		test.getObject(clazz.getName());
		Assert.fail("Attempting to get an object that was never added should fail");
	}
	
	/*
	 * ----------------------------------------------------------------------------
	 * Add Tests
	 * ----------------------------------------------------------------------------
	 */
	
	@Test
	public void testAddClass() throws Exception {
		test.addObject(clazz);
		Assert.assertEquals(clazz, test.getObject(clazz.getName()));
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testDuplicateClass() throws Exception {
		test.addObject(clazz);
		IObject clazz2 = new HBClass(clazz.getName());
		test.addObject(clazz2);
	}
	
	@Test
	public void testAddInterface() throws Exception {
		test.addObject(interf);
		Assert.assertEquals(interf, test.getObject(interf.getName()));
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testDuplicateInterface() throws Exception {
		test.addObject(interf);
		IObject interf2 = new HBInterface(interf.getName());
		test.addObject(interf2);
	}
	
	@Test (expected = HBDuplicateObjectFoundException.class)
	public void testDuplicateClassAndInterface() throws Exception {
		final String name = "DuppedName";
		IObject clazz1 = new HBClass(name);
		IObject interf1 = new HBInterface(name);
		
		test.addObject(clazz1);
		test.addObject(interf1);
	}
	
	@Test
	public void testDuplicateNameNotPackage() throws Exception {
		final String name = "DuppedName";
		final String pkg = "package";
		IObject clazz1 = new HBClass(name);
		test.addObject(clazz1);
		
		IObject clazz2 = new HBClass(name);
		clazz2.addPackage(pkg);
		Assert.assertTrue("Adding a class with a different package is fine", test.addObject(clazz2));
	}
	
	@Test
	public void testNormalAddRelationship_InterToClass() throws Exception {
		test.addObject(clazz);
		test.addObject(interf);		
		Assert.assertTrue("Adding a relationship normally should work", test.addRelationship(interf, clazz, impl));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_InterToClass() throws Exception {
		test.addObject(clazz);	
		test.addRelationship(interf, clazz, impl);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}
	
	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoTo_InterToClass() throws Exception{
		test.addObject(interf);
		test.addRelationship(interf, clazz, impl);
		Assert.fail("Shouldn't be able to add a relationship to a class that doesn't exist");
	}
	
	@Test
	public void testNormalAddRelationship_ClassToClass() throws Exception {
		test.addObject(clazz);
		test.addObject(clazz2);		
		Assert.assertTrue("Adding a relationship normally should work", test.addRelationship(clazz, clazz2, ext));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_ClassToClass() throws Exception {
		test.addObject(clazz2);	
		test.addRelationship(clazz, clazz2, ext);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoTo_ClassToClass() throws Exception {
		test.addObject(clazz);
		test.addRelationship(clazz, clazz2, ext);
		Assert.fail("Shouldn't be able to add a relationship to a class that doesn't exist");
	}
	
	@Test
	public void testNormalAddRelationship_InterToInter() throws Exception {
		test.addObject(interf);
		test.addObject(interf2);		
		Assert.assertTrue("Adding a relationship normally should work", test.addRelationship(interf, interf2, ext));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_InterToInter() throws Exception {
		test.addObject(interf2);	
		test.addRelationship(interf, interf2, ext);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoTo_InterToInter() throws Exception {
		test.addObject(interf);
		test.addRelationship(interf, interf2, ext);
		Assert.fail("Shouldn't be able to add a relationship to a class that doesn't exist");
	}
	
	// While these cases don't exist in java, we aren't checking that. The user is responsible. So the program shouldn't crash
	@Test
	public void testNormalAddRelationship_ClassToInter() throws Exception {
		test.addObject(clazz);
		test.addObject(interf);		
		Assert.assertTrue("Adding a relationship normally should work", test.addRelationship(clazz, interf, ext));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_ClassToInter() throws Exception {
		test.addObject(interf);	
		test.addRelationship(clazz, interf, ext);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoTo_ClassToInter() throws Exception {
		test.addObject(clazz);
		test.addRelationship(clazz, interf, ext);
		Assert.fail("Shouldn't be able to add a relationship to a class that doesn't exist");
	}
	
	@Test (expected = HBDuplicateRelationshipException.class)
	public void testAddDuplicateRelationship() throws Exception {
		test.addObject(clazz);
		test.addObject(clazz2);
		
		test.addRelationship(clazz, clazz2, ext);
		test.addRelationship(clazz, clazz2, impl);
	}
	// End non-Java versions
	
	/*
	 * ----------------------------------------------------------------------------
	 * Remove Tests
	 * ----------------------------------------------------------------------------
	 */
	@Test
	public void testRemoveObjectNorm_Class() throws Exception {
		test.addObject(clazz);
		Assert.assertTrue("Removing a class normally should work", test.removeObject(clazz));
	}
	
	@Test (expected = HBObjectNotFoundException.class) 
	public void testRemoveObjectNotFound_Class() throws Exception {
		test.removeObject(clazz);
	}
	
	@Test
	public void testRemoveObjectNorm_Inter() throws Exception {
		test.addObject(interf);
		Assert.assertTrue("Removing a class normally should work", test.removeObject(interf));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testRemoveObjectNotFound_Inter() throws Exception {
		test.removeObject(interf);
	}
	
	@Test
	public void testRemoveRelationshipNormal_ClassToClass() throws Exception {
		test.addObject(clazz);
		test.addObject(clazz2);
		test.addRelationship(clazz, clazz2, ext);
		
		Assert.assertTrue("Removing a relationship normally should work", test.removeRelationship(clazz, clazz2));
	}
	
	@Test
	public void testRemoveRelationshipNormal_ClassToInterface() throws Exception {
		test.addObject(clazz);
		test.addObject(interf);
		test.addRelationship(clazz, interf, impl);
		
		Assert.assertTrue("Removing a relationship normally should work", test.removeRelationship(clazz, interf));
	}
	
	@Test
	public void testRemoveRelationshipNormal_InterfaceToClass() throws Exception {
		test.addObject(interf);
		test.addObject(clazz);
		test.addRelationship(interf, clazz, impl);
		
		Assert.assertTrue("Removing a relationship normally should work", test.removeRelationship(interf, clazz));
	}
	
	@Test
	public void testRemoveRelationshipNormal_InterfaceToInterface() throws Exception {
		test.addObject(interf);
		test.addObject(interf2);
		test.addRelationship(interf, interf2, ext);
		
		Assert.assertTrue("Removing a relationship normally should work", test.removeRelationship(interf, interf2));
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipNoFrom() throws Exception {
		test.addObject(clazz);
		test.removeRelationship(interf, clazz);
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipNoTo() throws Exception {
		test.addObject(interf2);
		test.removeRelationship(interf2, clazz);
	}
	
	@Test (expected = HBRelationshipNotFoundException.class)
	public void testRemoveAlreadyRemovedRelationship() throws Exception {
		test.addObject(clazz);
		test.addObject(clazz2);
		test.addRelationship(clazz, clazz2, ext);
		
		test.removeRelationship(clazz, clazz2);
		test.removeRelationship(clazz, clazz2);
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipAfterRemoveFrom() throws Exception {
		test.addObject(interf);
		test.addObject(clazz);
		test.addRelationship(interf, clazz, impl);
		
		test.removeObject(interf);
		test.removeRelationship(interf, clazz);
	}
	
	@Test (expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipAfterRemoveTo() throws Exception {
		test.addObject(interf);
		test.addObject(clazz);
		test.addRelationship(interf, clazz, impl);
		
		test.removeObject(clazz);
		test.removeRelationship(interf, clazz);
	}
	
	@After
	public void tearDown() {
		test = null;
	}

}
