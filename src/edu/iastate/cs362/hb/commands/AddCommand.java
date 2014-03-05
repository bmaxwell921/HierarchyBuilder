package edu.iastate.cs362.hb.commands;

import java.util.ArrayList;

public class AddCommand implements ICommand {

	
	private Argument[] args;
	public ArrayList<Argument> arguments;
	public AddCommand()
	{
		initializeArgs();
	}
	
	private void initializeArgs()
	{
		args = new Argument[5];
		args[0] = new Argument("DESIGN", false, null);
		args[1] = new Argument("CLASS", false, null);
		args[2] = new Argument("INTERFACE", false, null);
		args[3] = new Argument("NAME", true, null);
		args[4] = new Argument("HELP", false, null);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCommand(String line) {
		// TODO Auto-generated method stub

	}

}
