package test.edu.iastate.cs362.hb.model.tree.impl;

import org.junit.After;
import org.junit.Before;

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
	
	
	
	@After
	public void tearDown() {
		test = null;
	}

}
