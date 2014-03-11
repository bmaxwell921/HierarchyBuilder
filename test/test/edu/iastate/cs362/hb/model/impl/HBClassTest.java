package test.edu.iastate.cs362.hb.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.impl.HBClass;

public class HBClassTest {
	
	private String NAME = "NAME";
	
	private IClass test;
	
	@Before
	public void setUp(){
		test = new HBClass(NAME);
	}
	
	@Test
	public void testRemovePackage(){
		String packageName = "PAKEAGE";
		test.addPackage(packageName);
		test.removePackage();
		Assert.assertTrue("Normal remove Package.", !(packageName.equals(test.getPackage())));
	}

	@After
	public void tearDown(){
		test = null;
	}
}
