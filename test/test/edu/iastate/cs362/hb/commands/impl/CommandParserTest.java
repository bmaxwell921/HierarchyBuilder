package test.edu.iastate.cs362.hb.commands.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.iastate.cs362.hb.commands.ICommand;
import edu.iastate.cs362.hb.commands.impl.CommandParser;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

public class CommandParserTest {
	
	@Test
	public void testCreate() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "CREATE -DESIGN -NAME Wobbly";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getName(), "create");
	}
	
	@Test
	public void testCreateDesign() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "CREATE -DESIGN -NAME Wobbly";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getSubCommand(), "design");
	}
	
	@Test
	public void testCreateDesignNamedCommand() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "CREATE -DESIGN -NAME Wobbly";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getFlagValue("name"), "Wobbly");
	}
	
	@Test
	public void addTest() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "ADD -CLASS -NAME NAME";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getName(), "add");
	}
	
	@Test
	public void addClass() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "ADD -CLASS -NAME NAME";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getSubCommand(), "class");
	}
	
	@Test
	public void addClassNamed() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "ADD -CLASS -NAME NAME";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getFlagValue("name"), "NAME");
	}
	
	@Test
	public void createDesign() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "CREATE -DESIGN";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getName(), "create");
	}
	
	@Test
	public void createDesignSubCommand() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "CREATE -DESIGN";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getSubCommand(), "design");
	}
	
	@Test
	public void createDesignNamed() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "CREATE -DESIGN -NaMe brandonsaloser";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getFlagValue("name"), "brandonsaloser");
	}
	
	@Test
	public void removeDesignNamed() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "Remove -DESIGN -NaMe brandonsaloser";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getFlagValue("name"), "brandonsaloser");
	
	}
	
	@Test
	public void removeDesign() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "Remove -DESIGN -NaMe brandonsaloser";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getName(), "remove");
	}
	
	@Test
	public void removeDesignSubtype() throws MalformattedCommandException
	{
		CommandParser parser = new CommandParser();
		String command = "Remove -DESIGN -NaMe brandonsaloser";
		ICommand c = parser.parseCommand(command);
		assertEquals(c.getName(), "remove");
	}

}
