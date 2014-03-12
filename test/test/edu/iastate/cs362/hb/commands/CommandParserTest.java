package test.edu.iastate.cs362.hb.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.iastate.cs362.hb.commands.CommandParser;
import edu.iastate.cs362.hb.commands.ICommand;
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

}
