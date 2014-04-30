package test.edu.iastate.cs362.hb.commands.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs362.hb.commands.ICommand;
import edu.iastate.cs362.hb.commands.impl.CommandParser;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

public class CommandParserTest {

	CommandParser test;

	@Before
	public void setUp() {
		test = new CommandParser();
	}

	@After
	public void tearDown() {
		test = null;
	}

	@Test
	public void testCreateDesign() throws MalformattedCommandException {
		String command = "create design -name Wobbly";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getName(), "create");
		assertEquals(c.getSubCommand(), "design");
		assertEquals(c.getFlagValue("name"), "Wobbly");
	}

	@Test
	public void testCreateDesignShort() throws MalformattedCommandException {
		String command = "create design -n Wobbly";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getName(), "create");
		assertEquals(c.getSubCommand(), "design");
		assertEquals(c.getFlagValue("name"), "Wobbly");
	}
	
	@Test (expected = MalformattedCommandException.class)
	public void testCreateInvalidSub() throws MalformattedCommandException {
		test.parseCommand("create asdfh");
	}
	
	@Test (expected = MalformattedCommandException.class)
	public void testCreateDesignInvalidFlag() throws MalformattedCommandException {
		test.parseCommand("create design -blah");
	}
	
	//@Test (expected = MalformattedCommandException.class)
	public void testCreateDesignInvalidNotGivenName() throws MalformattedCommandException {
		test.parseCommand("create design -name");
	}
	
	//@Test (expected = MalformattedCommandException.class)
	public void testCreateDesignInvalidNotGivenNameShort() throws MalformattedCommandException {
		test.parseCommand("create design -n");
	}

	@Test
	public void testCreateClass() throws MalformattedCommandException {
		String command = "create class -name name";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getName(), "create");
		assertEquals(c.getSubCommand(), "class");
		assertEquals(c.getFlagValue("name"), "name");
	}
	
	@Test
	public void testCreateClassShort() throws MalformattedCommandException {
		String command = "create class -n name";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getName(), "create");
		assertEquals(c.getSubCommand(), "class");
		assertEquals(c.getFlagValue("name"), "name");
	}
	
	//@Test (expected = MalformattedCommandException.class)
	public void testCreateClassInvalidNotGivenName() throws MalformattedCommandException {
		test.parseCommand("create class -name");
	}
	
	//@Test (expected = MalformattedCommandException.class)
	public void testCreateClassInvalidNotGivenNameShort() throws MalformattedCommandException {
		test.parseCommand("create class -n");
	}

	@Test
	public void listTest() throws MalformattedCommandException {
		String command = "list design";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getName(), "list");
	}

	@Test
	public void listTestName() throws MalformattedCommandException {
		String command = "list DESIGN";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getSubCommand(), "design");
	}

	@Test
	public void listClassTest() throws MalformattedCommandException {
		String command = "list CLASS -name className";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getSubCommand(), "class");
	}

	@Test
	public void listClassTest2() throws MalformattedCommandException {
		String command = "list cLaSs -name className";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getFlagValue("name"), "className");
	}

	@Test(expected = MalformattedCommandException.class)
	public void listTest3() throws MalformattedCommandException {
		String command = "list someGarbage";
		test.parseCommand(command);
	}

	@Test(expected = MalformattedCommandException.class)
	public void listTestException() throws MalformattedCommandException {
		String command = "ls";
		test.parseCommand(command);
	}

	@Test
	public void listNullTest() throws MalformattedCommandException {
		String command = "list class -name ";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getFlagValue("name"), null);
	}

	@Test
	public void listCacheTest() throws MalformattedCommandException {
		String command = "list cache -id 8675";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getFlagValue("id"), "8675");
	}

	@Test
	public void listCacheTest2() throws MalformattedCommandException {
		String command = "list cache -id 8675";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getName(), "list");
	}

	@Test
	public void listCacheTest3() throws MalformattedCommandException {
		String command = "list cache -id 8675";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getSubCommand(), "cache");
	}

	@Test
	public void listCacheException() throws MalformattedCommandException {
		String command = "list cache -id ";
		ICommand c = test.parseCommand(command);
		assertEquals(c.getFlagValue("id"), null);
	}

}
