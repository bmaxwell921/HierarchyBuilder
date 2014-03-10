package edu.iastate.cs362.hb.commands;

import edu.iastate.cs362.hb.model.attributes.Nameable;

/**
 * Interface representing basic behavior for commands. Commands have this format:
 * 
 * 		<CommandName> <SubCommand> -flag1 value1 -flag2 value2 ...
 * @author Brandon
 *
 */
public interface ICommand extends Nameable {
	
	/**
	 * REturns the subCommand for this command
	 * @return
	 */
	public String getSubCommand();
	
	/**
	 * Sets the subCommand as given
	 * @param subCommand
	 */
	public void setSubCommand(String subCommand);
	
	/**
	 * Returns whether this command has the given flag or not
	 * @param flagName
	 * @return
	 */
	public boolean hasFlag(String flagName);
	
	/**
	 * Gets the value associated with the given flag
	 * @param flagName
	 * @return
	 */
	public String getFlagValue(String flagName);
	
	/**
	 * Adds the given flagName and value to this command. Returns 
	 * true if the add was performed and false otherwise.
	 * 
	 * NOTE: should return false if a duplicate flagName is found
	 * @param flagName
	 * @param value
	 * @return
	 */
	public boolean addFlagValue(String flagName, String flagValue);
}
