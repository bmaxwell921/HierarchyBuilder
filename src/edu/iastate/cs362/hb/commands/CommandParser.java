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
		Boolean[] flagType = new Boolean[commandList.length];
		for(int i = 0; i < commandList.length; i++)
		{
			if(commandList[i].charAt(0) == '-')
			{
				flagType[i] = true;
			}
			else
			{
				flagType[i] = false;
			}
			//Force all toupper.
			commandList[i] = commandList[i].toLowerCase();
			
		}
		

		
		Command c;
		switch(commandList[0].substring(1))
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
		default:
			throw new MalformattedCommandException("Malformatted command name!", commandList[0]);	
		}
		
		
		for(int x = 1; x < commandList.length; x++)
		{
			switch(commandList[x].substring(1))
			{
			case CmdConstants.SubCmdNames.CLASS_REGEX:
				
				break;
			case CmdConstants.SubCmdNames.DESIGN_REGEX:
				
				break;
			case CmdConstants.SubCmdNames.INTERFACE_REGEX:
				
				break;
			case CmdConstants.SubCmdNames.METHOD_REGEX:
				
				break;
			case CmdConstants.SubCmdNames.PACKAGE_REGEX:
				break;
			
			}
			/**
			//Check to see if there is a next argument
			//If the next string is not null, and it's flagtype is false, then it is a value
			if((commandList[x + 1] != null)&&((flagType[x + 1] != null)&&(flagType[x+1] == false)))
			{
				
				c.addFlagValue(commandList[x].substring(1), commandList[x+1]);
				x++;
			}
			else
			{
				// TODO same as above
				c.addFlagValue(commandList[x].substring(1), null);
			}
			**/
		}
		
		
		return c;
	}
	
	public void loadConstants()
	{
		cmdNames.add(CmdConstants.CmdNames.ADD);
		cmdNames.add(CmdConstants.CmdNames.CREATE);
		cmdNames.add(CmdConstants.CmdNames.EXIT);
		
		subNames.add(CmdConstants.SubCmdNames.CLASS_REGEX);
		subNames.add(CmdConstants.SubCmdNames.DESIGN_REGEX);
		subNames.add(CmdConstants.SubCmdNames.INTERFACE_REGEX);
		subNames.add(CmdConstants.SubCmdNames.METHOD_REGEX);
		subNames.add(CmdConstants.SubCmdNames.PACKAGE_REGEX);
		
	}

}
