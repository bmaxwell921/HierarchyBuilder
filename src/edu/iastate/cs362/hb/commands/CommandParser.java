package edu.iastate.cs362.hb.commands;


import java.util.ArrayList;

import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

/**
 * Class used to parse a given string into a command
 * @author Brandon
 *
 */
public class CommandParser implements ICommandParser{

	ArrayList<String> cmdNames;
	ArrayList<String> subNames;
	
	public CommandParser()
	{
		cmdNames = new ArrayList<String>();
		subNames = new ArrayList<String>();
		loadConstants();
	}
	
	@Override
	public ICommand parseCommand(String command) throws MalformattedCommandException {
		
		String[] commandList = command.split(" ");
		for(int i = 0; i < commandList.length; i++)
		{

			//Force all toupper.
			commandList[i] = commandList[i].toLowerCase();
			
		}
		

		
		Command c;
		switch(commandList[0])
		{
		case CmdConstants.CmdNames.ADD:
			c = new Command(CmdConstants.CmdNames.ADD);
			break;
		case CmdConstants.CmdNames.CREATE:
			c = new Command(CmdConstants.CmdNames.CREATE);
			break;
		case CmdConstants.CmdNames.EXIT:
			c = new Command(CmdConstants.CmdNames.EXIT);
			break;
		case CmdConstants.CmdNames.REMOVE:
			c = new Command(CmdConstants.CmdNames.REMOVE);
			break;
		default:
			throw new MalformattedCommandException("Malformatted command name!", commandList[0]);	
		}
		
		for(int i = 1; i < commandList.length; i++)
		{
			if(commandList[i].charAt(0) == '-')
			{
				//Flag
				boolean valueUsed = addFlag(c, commandList[i].substring(1), commandList[i+1]);
				if(valueUsed)
				{
					i++;
				}
			}
			else
			{
				//SubCommand
				addSubCommand(c, commandList[i]);
			}
		}
		
		
		return c;
	}
	
	public void loadConstants()
	{
		cmdNames.add(CmdConstants.CmdNames.ADD);
		cmdNames.add(CmdConstants.CmdNames.CREATE);
		cmdNames.add(CmdConstants.CmdNames.EXIT);
		cmdNames.add(CmdConstants.CmdNames.REMOVE);
		
		subNames.add(CmdConstants.SubCmdNames.CLASS_REGEX);
		subNames.add(CmdConstants.SubCmdNames.DESIGN_REGEX);
		subNames.add(CmdConstants.SubCmdNames.INTERFACE_REGEX);
		subNames.add(CmdConstants.SubCmdNames.METHOD_REGEX);
		subNames.add(CmdConstants.SubCmdNames.PACKAGE_REGEX);
		
	}

	public boolean addFlag(Command c, String input, String value) throws MalformattedCommandException
	{
		boolean valueUsed = false;
		if(input.matches(CmdConstants.Flags.CONTAINER_NAME))
		{
			c.addFlagValue(CmdConstants.Flags.CONTAINER_NAME, value);
			valueUsed = true;
		}
		else if(input.matches(CmdConstants.Flags.HELP_REGEX))
		{		
			c.addFlagValue(CmdConstants.Flags.HELP, null);
		}
		else if(input.matches(CmdConstants.Flags.INSTANCE_REGEX))
		{
			c.addFlagValue(CmdConstants.Flags.INSTANCE, null);
		}
		else if(input.matches(CmdConstants.Flags.NAME_REGEX))
		{
			c.addFlagValue(CmdConstants.Flags.NAME, value);
			valueUsed = true;
		}
		else if(input.matches(CmdConstants.Flags.PARAMETERS_REGEX))
		{
			c.addFlagValue(CmdConstants.Flags.PARAMETERS, value);
			valueUsed = true;
		}
		else if(input.matches(CmdConstants.Flags.STATIC_REGEX))
		{
			c.addFlagValue(CmdConstants.Flags.STATIC, null);
		}
		else
		{
			throw new MalformattedCommandException("Malformatted flag values!", input, value);
		}
		
		return valueUsed;
	}
	
	public void addSubCommand(Command c, String input) throws MalformattedCommandException
	{
			if(input.matches(CmdConstants.SubCmdNames.CLASS_REGEX))
			{
				c.setSubCommand(CmdConstants.SubCmdNames.CLASS);
			}
			else if(input.matches(CmdConstants.SubCmdNames.DESIGN_REGEX))
			{
				c.setSubCommand(CmdConstants.SubCmdNames.DESIGN);
			}
			else if(input.matches(CmdConstants.SubCmdNames.INTERFACE_REGEX))
			{
				c.setSubCommand(CmdConstants.SubCmdNames.INTERFACE);
			}
			else if(input.matches(CmdConstants.SubCmdNames.METHOD_REGEX))
			{
				c.setSubCommand(CmdConstants.SubCmdNames.METHOD);
			}
			else if(input.matches(CmdConstants.SubCmdNames.PACKAGE_REGEX))
			{
				c.setSubCommand(CmdConstants.SubCmdNames.PACKAGE);
			}
			else if(input.matches(CmdConstants.SubCmdNames.RELATIONSHIP_REGEX))
			{
				c.setSubCommand(CmdConstants.SubCmdNames.RELATIONSHIP);
			}
			else
			{
				throw new MalformattedCommandException("Malformatted sub-command name!", input);
			}
			
			
		
	}
}
