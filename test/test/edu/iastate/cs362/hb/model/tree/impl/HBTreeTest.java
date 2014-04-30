package test.edu.iastate.cs362.hb.model.tree.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import edu.iastate.cs362.hb.model.tree.IHBTreeVisitor;
import edu.iastate.cs362.hb.model.tree.Pair;
import edu.iastate.cs362.hb.model.tree.impl.HBTree;

/**
 * Tests for the HBTree class
 * 
 * @author Brandon
 * 
 */
public class HBTreeTest {

	// THE TREE!
	private HBTree test;
	
	private int id = 0;

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
		id = 0;
		clazz.setId(id++);
		clazz2.setId(id++);
		interf.setId(id++);
		interf2.setId(id++);
	}

	/*
	 * --------------------------------------------------------------------------
	 * -- Get Tests
	 * --------------------------------------------------------------
	 * --------------
	 */

	@Test
	public void testGetObject() throws Exception {
		test.addObject(clazz);
		IObject getRes = test.getObject(0);
		Assert.assertEquals("Getting an object that was just added should work", clazz, getRes);
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testGetObjectNotFound() throws Exception {
		test.getObject(0);
		Assert.fail("Attempting to get an object that was never added should fail");
	}

	/*
	 * --------------------------------------------------------------------------
	 * -- Add Tests
	 * --------------------------------------------------------------
	 * --------------
	 */

	@Test
	public void testAddClass() throws Exception {
		test.addObject(clazz);
		long id = 0;
		Assert.assertEquals(clazz, test.getObject(id));
	}

	@Test(expected = HBDuplicateObjectFoundException.class)
	public void testDuplicateClass() throws Exception {
		test.addObject(clazz);
		IObject clazz2 = new HBClass(clazz.getName());
		test.addObject(clazz2);
	}

	@Test
	public void testAddInterface() throws Exception {
		test.addObject(interf);
		Assert.assertEquals(interf, test.getObject(interf.getId()));
	}

	@Test(expected = HBDuplicateObjectFoundException.class)
	public void testDuplicateInterface() throws Exception {
		test.addObject(interf);
		IObject interf2 = new HBInterface(interf.getName());
		interf2.setId(interf.getId());
		test.addObject(interf2);
	}

	@Test(expected = HBDuplicateObjectFoundException.class)
	public void testDuplicateClassAndInterface() throws Exception {
		final String name = "DuppedName";
		IObject clazz1 = new HBClass(name);
		IObject interf1 = new HBInterface(name);

		test.addObject(clazz1);
		test.addObject(interf1);
	}

	@Test
	public void testNormalAddRelationship_InterToClass() throws Exception {
		test.addObject(clazz);
		test.addObject(interf);
		Assert.assertTrue("Adding a relationship normally should work", test.addRelationship(interf, clazz, impl));
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_InterToClass() throws Exception {
		test.addObject(clazz);
		test.addRelationship(interf, clazz, impl);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoTo_InterToClass() throws Exception {
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

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_ClassToClass() throws Exception {
		test.addObject(clazz2);
		test.addRelationship(clazz, clazz2, ext);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}

	@Test(expected = HBObjectNotFoundException.class)
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

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_InterToInter() throws Exception {
		test.addObject(interf2);
		test.addRelationship(interf, interf2, ext);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoTo_InterToInter() throws Exception {
		test.addObject(interf);
		test.addRelationship(interf, interf2, ext);
		Assert.fail("Shouldn't be able to add a relationship to a class that doesn't exist");
	}

	// While these cases don't exist in java, we aren't checking that. The user
	// is responsible. So the program shouldn't crash
	@Test
	public void testNormalAddRelationship_ClassToInter() throws Exception {
		test.addObject(clazz);
		test.addObject(interf);
		Assert.assertTrue("Adding a relationship normally should work", test.addRelationship(clazz, interf, ext));
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoFrom_ClassToInter() throws Exception {
		test.addObject(interf);
		test.addRelationship(clazz, interf, ext);
		Assert.fail("Shouldn't be able to add a relationship from an interface that doesn't exist");
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testAddRelationshipNoTo_ClassToInter() throws Exception {
		test.addObject(clazz);
		test.addRelationship(clazz, interf, ext);
		Assert.fail("Shouldn't be able to add a relationship to a class that doesn't exist");
	}

	@Test(expected = HBDuplicateRelationshipException.class)
	public void testAddDuplicateRelationship() throws Exception {
		test.addObject(clazz);
		test.addObject(clazz2);

		test.addRelationship(clazz, clazz2, ext);
		test.addRelationship(clazz, clazz2, impl);
	}

	// End non-Java versions

	/*
	 * --------------------------------------------------------------------------
	 * -- Remove Tests
	 * ----------------------------------------------------------
	 * ------------------
	 */
	@Test
	public void testRemoveObjectNorm_Class() throws Exception {
		test.addObject(clazz);
		Assert.assertTrue("Removing a class normally should work", test.removeObject(clazz));
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testRemoveObjectNotFound_Class() throws Exception {
		test.removeObject(clazz);
	}

	@Test
	public void testRemoveObjectNorm_Inter() throws Exception {
		test.addObject(interf);
		Assert.assertTrue("Removing a class normally should work", test.removeObject(interf));
	}

	@Test(expected = HBObjectNotFoundException.class)
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

	@Test(expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipNoFrom() throws Exception {
		test.addObject(clazz);
		test.removeRelationship(interf, clazz);
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipNoTo() throws Exception {
		test.addObject(interf2);
		test.removeRelationship(interf2, clazz);
	}

	@Test(expected = HBRelationshipNotFoundException.class)
	public void testRemoveAlreadyRemovedRelationship() throws Exception {
		test.addObject(clazz);
		test.addObject(clazz2);
		test.addRelationship(clazz, clazz2, ext);

		test.removeRelationship(clazz, clazz2);
		test.removeRelationship(clazz, clazz2);
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipAfterRemoveFrom() throws Exception {
		test.addObject(interf);
		test.addObject(clazz);
		test.addRelationship(interf, clazz, impl);

		test.removeObject(interf);
		test.removeRelationship(interf, clazz);
	}

	@Test(expected = HBObjectNotFoundException.class)
	public void testRemoveRelationshipAfterRemoveTo() throws Exception {
		test.addObject(interf);
		test.addObject(clazz);
		test.addRelationship(interf, clazz, impl);

		test.removeObject(clazz);
		test.removeRelationship(interf, clazz);
	}

	@Test
	public void testTraverseSingle_Class() throws Exception {
		test.addObject(clazz);
		HBTreeAccum accumulator = new HBTreeAccum();
		test.traverse(accumulator);

		List<IObject> correct = new ArrayList<IObject>() {
			{
				add(clazz);
			}
		};
		Assert.assertEquals(correct, accumulator.objs);
	}

	@Test
	public void testTraverseTwoAlphabetical_Class() throws Exception {
		final IObject a = new HBClass("a");
		a.setId(id++);
		final IObject b = new HBClass("b");
		b.setId(id++);

		test.addObject(a);
		test.addObject(b);

		HBTreeAccum accumulator = new HBTreeAccum();
		test.traverse(accumulator);

		List<IObject> correct = new ArrayList<IObject>() {
			{
				add(a);
				add(b);
			}
		};
		Assert.assertEquals(correct, accumulator.objs);
	}

	@Test
	public void testTraverseSingle_Inter() throws Exception {
		test.addObject(interf);
		HBTreeAccum accumulator = new HBTreeAccum();
		test.traverse(accumulator);

		List<IObject> correct = new ArrayList<IObject>() {
			{
				add(interf);
			}
		};
		Assert.assertEquals(correct, accumulator.objs);
	}

	@Test
	public void testTraverseTwoAlphabetical_Inter() throws Exception {
		final IObject a = new HBInterface("a");
		a.setId(id++);
		final IObject b = new HBInterface("b");
		b.setId(id++);

		test.addObject(a);
		test.addObject(b);

		HBTreeAccum accumulator = new HBTreeAccum();
		test.traverse(accumulator);

		List<IObject> correct = new ArrayList<IObject>() {
			{
				add(a);
				add(b);
			}
		};
		Assert.assertEquals(correct, accumulator.objs);
	}

	@Test
	public void testTraverseMulti_Mixed() throws Exception {
		List<IObject> correct = new ArrayList<>();

		// Set them up
		for (int i = 0; i < 26; ++i) {
			IObject add;
			if (i % 2 == 0) {
				add = new HBClass("" + (char) ('a' + i));
			} else {
				add = new HBInterface("" + (char) ('a' + i));
			}
			add.setId(id++);
			test.addObject(add);
			correct.add(add);
		}

		HBTreeAccum accumulator = new HBTreeAccum();
		test.traverse(accumulator);
		Assert.assertEquals(correct, accumulator.objs);
	}

	@After
	public void tearDown() {
		test = null;
	}

	// Visitor that just stores the objects that were visited, in the order they
	// were visited
	private class HBTreeAccum implements IHBTreeVisitor {

		// The visited objs
		public final List<IObject> objs;

		public HBTreeAccum() {
			objs = new ArrayList<>();
		}

		@Override
		public void visit(IObject o, Set<Pair<IRelationship, IObject>> superTypes) {
			objs.add(o);
		}
	}

}
