package edu.iastate.cs362.hb.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Created command with name and value
 * @author rclabough
 *
 */
public class Command implements ICommand {

	private String name;
	private Map<String, String> value;
	
	public Command(String name)
	{
		this.name = name;
		this.value = new HashMap<String, String>();
	}
	
	public void AddArgument(String name, String value)
	{
		this.value.put(name, value);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setCommand(String line) {
		// TODO Auto-generated method stub

	}

}
