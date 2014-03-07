package edu.iastate.cs362.hb.commands;

/**
 * Class used to parse a given string into a command
 * @author Brandon
 *
 */
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
				/*
				 *  TODO this returns a boolean as to whether it was added or not. If 
				 *  it returns false then the string was invalid
				 */
				c.addFlagValue(commandList[x], commandList[x+1]);
				x++;
			}
			else
			{
				// TODO same as above
				c.addFlagValue(commandList[x], null);
			}
			
		}
		
		
		return null;
	}
	
	

}
