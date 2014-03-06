package edu.iastate.cs362.hb.commands;

/**
 * Created command with name and value
 * @author rclabough
 *
 */
public class Command implements ICommand {

	private String name;
	private String value;
	
	public Command(String name, String value)
	{
		this.name = name;
		this.value = value;
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
