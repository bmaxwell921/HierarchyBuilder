package edu.iastate.cs362.hb.commands;

import java.util.ArrayList;

import edu.iastate.cs362.hb.constants.*;

public class CommandParser implements ICommandParser{

	@Override
	public ICommand parseCommand(String command) {
		
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
		}
		Command c = new Command(commandList[0]);
		
		
		for(int x = 0; x < commandList.length; x++)
		{
			//Check to see if there is a next argument
			//If the next string is not null, and it's flagtype is false, then it is a value
			if((commandList[x + 1] != null)&&((flagType[x + 1] != null)&&(flagType[x+1] == false)))
			{
				c.AddArgument(commandList[x], commandList[x+1]);
				x++;
			}
			else
			{
				c.AddArgument(commandList[x], null);
			}
			
		}
		
		
		return null;
	}
	
	

}
