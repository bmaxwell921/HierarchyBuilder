package edu.iastate.cs362.hb.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Created command with name and value
 * 
 * @author rclabough, Brandon
 * 
 */
public class Command implements ICommand {

	// The main name of the command
	private String name;

	// The sub name of the command
	private String subCmd;

	// Mapping of flags to their values
	private Map<String, String> value;

	public Command(String name) {
		this.name = name;
		this.value = new HashMap<String, String>();
	}

	public void AddArgument(String name, String value) {
		this.value.put(name, value);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name != null && this.name.equals(name);
	}

	@Override
	public String getSubCommand() {
		return subCmd;
	}

	@Override
	public void setSubCommand(String subCommand) {
		this.subCmd = subCommand;
	}

	@Override
	public boolean hasFlag(String flagName) {
		return value.containsKey(flagName);
	}

	@Override
	public String getFlagValue(String flagName) {
		return value.get(flagName);
	}

	@Override
	public boolean addFlagValue(String flagName, String flagValue) {
		if (value.containsKey(flagName)) {
			return false;
		}
		value.put(flagName, flagValue);
		return true;
	}

}
