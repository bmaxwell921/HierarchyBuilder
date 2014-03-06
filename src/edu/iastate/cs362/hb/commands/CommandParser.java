package edu.iastate.cs362.hb.commands;

import java.util.ArrayList;

import edu.iastate.cs362.hb.constants.*;

public class CommandParser implements ICommandParser{

	@Override
	public ICommand parseCommand(String command) {
		CmdConstants constant = new CmdConstants();
		
		String[] args = command.split(" ");
		ArrayList<String> argList= new ArrayList<String>();
		
		ArrayList<Argument> usedArgs = new ArrayList<Argument>();
		for(String s : args)
		{
			argList.add(s);
		}
		int index = 0;
		switch(argList.get(index).charAt(0))
		{
		case '-':
			default:
			//Switches
			//Find the argument that matches this
			for(Argument a : constant.getArgs())
			{
				boolean use = a.handle(argList.subList(0, argList.size() - 1));
				if(use)
				{
					usedArgs.add(a);
				}
			}
			break;
		}
		
		
		//Recursively get all used arguments
		ArrayList<Command> commands = new ArrayList<Command>();
		for(Argument a : usedArgs)
		{
			commands.addAll(a.getCommands());
		}
		
		return null;
	}
	
	

}
