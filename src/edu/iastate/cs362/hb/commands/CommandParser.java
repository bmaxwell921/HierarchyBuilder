package edu.iastate.cs362.hb.commands;

public class CommandParser implements ICommandParser{

	@Override
	public ICommand parseCommand(String command) {
		// Check first string
		String createString = "CREATE";
		String addString = "ADD";
		
		if(command.toUpperCase().startsWith(createString))
		{
			ICommand cmd = new CreateCommand();
			cmd.setCommand(command.substring(createString.length()));
			return cmd;
		}
		else if(command.toUpperCase().startsWith(addString))
		{
			//return addCommand(command.substring(addString.length()));
		}
		
		
		return null;
	}
	
	

}
