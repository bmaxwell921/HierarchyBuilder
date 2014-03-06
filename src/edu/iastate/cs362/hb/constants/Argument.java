package edu.iastate.cs362.hb.constants;

import java.util.ArrayList;
import java.util.List;

import edu.iastate.cs362.hb.commands.Command;

public class Argument 
{
	public Argument(String aName, boolean aValueField, String aValue, boolean aUse)
	{
		name = aName;
		hasValueField = aValueField;
		use = aUse;
		subArguments = new ArrayList<Argument>();
		usedArguments = new ArrayList<Argument>();
	}
	
	public void addArgument(Argument a)
	{
		subArguments.add(a);
	}
	
	public void addArguments(ArrayList<Argument> list)
	{
		subArguments = list;
	}
	
	public boolean handle(List<String> list)
	{
		if(list.get(0).equals(name))
		{
			//For example, after -Name we expect the name
			if(hasValueField)
			{
				this.value = list.get(1);
				
				//If we are expecting more arguments, like in CREATE
				if(subArguments != null)
				{
					//Handle all following sub arguments
					for(Argument a : subArguments)
					{
						boolean use = a.handle(list.subList(2, list.size() - 1));
						if(use)
						{
							usedArguments.add(a);
						}
						
					}
				}
			}
			else
			{
				//If we are expecting more arguments, like in CREATE
				if(subArguments != null)
				{
					//Handle all following sub arguments
					for(Argument a : subArguments)
					{
						boolean use = a.handle(list.subList(1, list.size() - 1));
						if(use)
						{
							usedArguments.add(a);
						}
					}
				}
			}
			use = true;
			return use;
		}
		else
		{
			//This is not the correct argument
			return false;
		}
	}
	
	public ArrayList<Command> getCommands()
	{
		ArrayList<Command> list = new ArrayList<Command>();
		list.add(new Command(this.name, this.value));
		if(!usedArguments.isEmpty())
		{
			for(Argument a : usedArguments)
			{
				list.addAll(a.getCommands());
			}
		}
		return list;
	}
	
	public String name;
	
	public boolean hasValueField;
	
	public String value;
	
	public boolean use;
	
	public ArrayList<Argument> subArguments;
	
	public ArrayList<Argument> usedArguments;
	
	

}
